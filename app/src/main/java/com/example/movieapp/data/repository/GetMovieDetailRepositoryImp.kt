package com.example.movieapp.data.repository

import com.example.movieapp.data.mapper.MovieDetailDataToDomainMapper
import com.example.movieapp.data.retrofit.MovieApi
import com.example.movieapp.data.util.NetworkResult
import com.example.movieapp.data.util.handleApi
import com.example.movieapp.domain.model.MovieDetailDomainModel
import com.example.movieapp.domain.repository.GetMovieDetailRespository

class GetMovieDetailRepositoryImp(
    private val api: MovieApi,
    private val mapper: MovieDetailDataToDomainMapper,
) : GetMovieDetailRespository {
    override suspend fun getMovieDetail(id: Long): NetworkResult<MovieDetailDomainModel> {
        return when (val response = handleApi { api.getMovieDetail(id) }) {
            is NetworkResult.Error -> NetworkResult.Error(response.code, response.message)
            is NetworkResult.Exception -> NetworkResult.Exception(response.e)
            is NetworkResult.Success -> NetworkResult.Success(mapper.toDomain(response.data))
        }
    }

}