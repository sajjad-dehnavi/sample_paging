package dehnavi.sajjad.paging.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dehnavi.sajjad.paging.model.Data
import dehnavi.sajjad.paging.model.ResponseMovie
import dehnavi.sajjad.paging.repository.MoviesRepository
import javax.inject.Inject

class MoviePagingSource @Inject constructor(private val repository: MoviesRepository) :
    PagingSource<Int, Data>() {

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val currentPage = params.key ?: 1
            val response = repository.getAllMovies(currentPage)
            val data = response.body()?.data ?: emptyList()
            val responseData = mutableListOf<Data>()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}