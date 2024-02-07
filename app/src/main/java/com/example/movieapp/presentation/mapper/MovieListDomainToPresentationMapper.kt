package com.example.movieapp.presentation.mapper

import com.example.movieapp.domain.model.MovieDomainModel
import com.example.movieapp.presentation.model.MoviePresentationModel

class MovieListDomainToPresentationMapper {

    fun toPresentation(list: List<MovieDomainModel>): List<MoviePresentationModel> {
        return list.map {
            MoviePresentationModel(
                id = it.id,
                title = it.title,
                rate = it.rate,
                image = it.image
            )
        }
    }
}