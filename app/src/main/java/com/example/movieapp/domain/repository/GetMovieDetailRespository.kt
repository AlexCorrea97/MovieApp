package com.example.movieapp.domain.repository

import com.example.movieapp.data.util.NetworkResult
import com.example.movieapp.domain.model.MovieDetailDomainModel

interface GetMovieDetailRespository {
    suspend fun getMovieDetail(id: Long): NetworkResult<MovieDetailDomainModel>
}