package com.example.movieapp.data.repository

import com.example.movieapp.data.mapper.MovieDataToDomainMapper
import com.example.movieapp.data.model.MovieListResponseDataClass
import com.example.movieapp.data.retrofit.MovieApi
import com.example.movieapp.data.util.NetworkResult
import com.example.movieapp.data.util.handleApi
import com.example.movieapp.domain.model.MovieDomainModel
import com.example.movieapp.domain.repository.GetMoviesRepository

class GetMoviesRepositoryImp(
    private val movieApi: MovieApi,
    private val mapper: MovieDataToDomainMapper
): GetMoviesRepository {
    override suspend fun getMovies(): NetworkResult<List<MovieDomainModel>> {
        val response = handleApi {
            movieApi.getMovies()
        }
        when(response){
            is NetworkResult.Error -> return NetworkResult.Error(response.code, response.message)
            is NetworkResult.Exception -> return NetworkResult.Exception(response.e)
            is NetworkResult.Success -> return NetworkResult.Success(mapper.toDomain(response.data.result ?: emptyList()))
        }
    }
}