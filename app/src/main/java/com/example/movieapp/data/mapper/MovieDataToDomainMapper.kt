package com.example.movieapp.data.mapper

import com.example.movieapp.data.model.MovieItemResponseDataClass
import com.example.movieapp.domain.model.MovieDomainModel

class MovieDataToDomainMapper {

    fun toDomain(movieListDataClass: List<MovieItemResponseDataClass>): List<MovieDomainModel> {
        return movieListDataClass.map {
            MovieDomainModel(
                id = it.id ?: 0,
                title = it.title?: "Not Found",
                image = it.image ?: "",
                rate = it.voteAverage ?: 0.0
            )
        }
    }
}