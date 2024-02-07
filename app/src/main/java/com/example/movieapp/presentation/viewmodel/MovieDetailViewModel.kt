package com.example.movieapp.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.util.NetworkResult
import com.example.movieapp.domain.use_case.GetMovieDetailUseCase
import com.example.movieapp.presentation.mapper.MovieDetailDomainToPresentationMapper
import com.example.movieapp.presentation.model.MovieDetailPresentationModel
import com.example.movieapp.presentation.util.BaseViewModel
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val useCase: GetMovieDetailUseCase,
    private val mapper: MovieDetailDomainToPresentationMapper
): BaseViewModel<MovieDetailViewState>(MovieDetailViewState()) {

    fun getMovieDetail(movieId: Long) {
        updateViewState { saveMovieId(movieId) }

        updateViewState { onLoading() }

        viewModelScope.launch {
            val response = useCase(movieId)
            when (response) {
                is NetworkResult.Error -> updateViewState{ onError() }
                is NetworkResult.Exception -> updateViewState{ onError() }
                is NetworkResult.Success -> updateViewState { onResult(mapper.toPresentation(response.data)) }
            }
        }
    }
}

data class MovieDetailViewState(
    val isLoading: Boolean = false,
    val movieId: Long = 0,
    val onResult: MovieDetailPresentationModel? = null,
    val hasError: Boolean = false
) {
    fun saveMovieId(movieId: Long) = copy(movieId = movieId)

    fun onLoading() = copy(isLoading = true)

    fun onResult(movieDetailPresentationModel: MovieDetailPresentationModel) = copy(isLoading = false, onResult= movieDetailPresentationModel, hasError = false)

    fun onError() = copy(isLoading = false, onResult = null, hasError= true)
}