package dehnavi.sajjad.paging.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dehnavi.sajjad.paging.adapters.LoadMoreStateAdapter
import dehnavi.sajjad.paging.adapters.MoviesAdapter
import dehnavi.sajjad.paging.databinding.ActivityMainBinding
import dehnavi.sajjad.paging.viewmodel.MoviesViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //binding
    private lateinit var binding: ActivityMainBinding

    //other
    @Inject
    lateinit var moviesAdapter: MoviesAdapter
    private val viewModel: MoviesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            //Loading
            lifecycleScope.launchWhenCreated {
                moviesAdapter.loadStateFlow.collect {
                    val state = it.refresh
                    loading.isVisible = state is LoadState.Loading
                }
            }

            //Recycler view
            recycleMovie.apply {
                adapter = moviesAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
            //load data
            lifecycleScope.launchWhenCreated {
                viewModel.moviesList.collect { data ->
                    moviesAdapter.submitData(data)

                }
            }

            //swipe
            swipe.setOnRefreshListener {
                swipe.isRefreshing = false
                moviesAdapter.refresh()
            }

            //Load more
            recycleMovie.adapter = moviesAdapter.withLoadStateFooter(
                LoadMoreStateAdapter {
                    moviesAdapter.retry()
                }
            )
        }

    }
}