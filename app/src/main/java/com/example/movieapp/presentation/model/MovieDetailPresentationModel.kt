package com.example.movieapp.presentation.model

data class MovieDetailPresentationModel(
    val id: Long,
    val image: String,
    val title: String,
    val releaseDate: String,
    val runTime: Int,
    val overview: String,
    val isAdult: Boolean,
    val genderList: List<String>
)
