package com.example.movieapp.data.retrofit

import com.example.movieapp.data.model.MovieDetailResponseDataModel
import com.example.movieapp.data.model.MovieListResponseDataClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {
    @GET("movie/now_playing")
    suspend fun getMovies(): Response<MovieListResponseDataClass>

    @GET("movie/{image_id}")
    suspend fun getMovieDetail(@Path(value = "image_id", encoded = false) imageId: Long): Response<MovieDetailResponseDataModel>
}