package com.example.movieapp.domain.model

data class MovieDetailDomainModel(
    val id: Long,
    val image: String,
    val title: String,
    val releaseDate: String,
    val runTime: Int,
    val overview: String,
    val isAdult: Boolean,
    val genderList: List<String>
)