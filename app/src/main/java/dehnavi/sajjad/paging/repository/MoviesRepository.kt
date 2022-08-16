package dehnavi.sajjad.paging.repository

import dehnavi.sajjad.paging.api.ApiServices
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val apiServices: ApiServices) {
    suspend fun getAllMovies(page: Int) = apiServices.getAllMovies(page)
}