package com.example.movieapp.domain.repository

import com.example.movieapp.data.model.MovieListResponseDataClass
import com.example.movieapp.data.util.NetworkResult
import com.example.movieapp.domain.model.MovieDomainModel

interface GetMoviesRepository {
    suspend fun getMovies() : NetworkResult<List<MovieDomainModel>>
}