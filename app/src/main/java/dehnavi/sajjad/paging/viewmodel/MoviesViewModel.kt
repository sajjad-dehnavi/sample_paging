package dehnavi.sajjad.paging.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dehnavi.sajjad.paging.paging.MoviePagingSource
import dehnavi.sajjad.paging.repository.MoviesRepository
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: MoviesRepository) : ViewModel() {

    val moviesList = Pager(PagingConfig(1)){
        MoviePagingSource(repository)
    }.flow.cachedIn(viewModelScope)
}