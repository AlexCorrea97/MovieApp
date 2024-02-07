package com.example.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class MovieListResponseDataClass(
    @SerializedName("status_message") val statusMessage: String? = null,
    @SerializedName("success") val isSuccessfull: Boolean? = null,
    @SerializedName("page") val page: Int? = null,
    @SerializedName("total_pages") val totalPages: Int? = null,
    @SerializedName("results") val result: List<MovieItemResponseDataClass>? = null,

)

data class MovieItemResponseDataClass(
    @SerializedName("adult") val isAdult: Boolean = false,
    @SerializedName("id") val id: Long? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("vote_average") val voteAverage: Double? = null,
    @SerializedName("poster_path") val image: String? = null

)
