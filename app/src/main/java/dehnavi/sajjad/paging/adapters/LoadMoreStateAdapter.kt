package dehnavi.sajjad.paging.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import dehnavi.sajjad.paging.databinding.LoadMoreBinding

class LoadMoreStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<LoadMoreStateAdapter.ViewHolder>() {

    private lateinit var binding: LoadMoreBinding


    inner class ViewHolder(retry: () -> Unit) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btnRetry.setOnClickListener { retry() }
        }

        fun bindData(loadState: LoadState) {
            binding.apply {
                loadMore.isVisible = loadState is LoadState.Loading
                txtError.isVisible = loadState is LoadState.Error
                btnRetry.isVisible = loadState is LoadState.Error
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bindData(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        binding = LoadMoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(retry)
    }
}