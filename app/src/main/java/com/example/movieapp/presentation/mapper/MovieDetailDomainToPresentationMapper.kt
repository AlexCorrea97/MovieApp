package com.example.movieapp.presentation.mapper

import com.example.movieapp.domain.model.MovieDetailDomainModel
import com.example.movieapp.presentation.model.MovieDetailPresentationModel

class MovieDetailDomainToPresentationMapper {
    fun toPresentation(moviewDetailDomainModel: MovieDetailDomainModel): MovieDetailPresentationModel {
        return MovieDetailPresentationModel(
            id = moviewDetailDomainModel.id,
            genderList = moviewDetailDomainModel.genderList,
            image = moviewDetailDomainModel.image,
            isAdult = moviewDetailDomainModel.isAdult,
            overview = moviewDetailDomainModel.overview,
            releaseDate = moviewDetailDomainModel.releaseDate,
            runTime = moviewDetailDomainModel.runTime,
            title = moviewDetailDomainModel.title
        )
    }
}