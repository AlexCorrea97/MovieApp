package com.example.movieapp.data.mapper

import com.example.movieapp.data.model.MovieDetailResponseDataModel
import com.example.movieapp.domain.model.MovieDetailDomainModel

class MovieDetailDataToDomainMapper {
    fun toDomain(movieDetail: MovieDetailResponseDataModel): MovieDetailDomainModel {
        return MovieDetailDomainModel(
            id = movieDetail.id ?: 0,
            genderList = movieDetail.genres.map { it.name ?: "" },
            image = movieDetail.posterPath ?: "",
            isAdult = movieDetail.adult ?: false,
            overview = movieDetail.overview ?: "",
            releaseDate = movieDetail.releaseDate ?: "",
            runTime = movieDetail.runtime ?: 0,
            title = movieDetail.title ?: ""

        )
    }
}