package com.example.movieapp.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.util.NetworkResult
import com.example.movieapp.domain.use_case.GetMoviesUseCase
import com.example.movieapp.presentation.mapper.MovieListDomainToPresentationMapper
import com.example.movieapp.presentation.model.MoviePresentationModel
import com.example.movieapp.presentation.util.BaseViewModel
import kotlinx.coroutines.launch

class MoviesListViewModel(
    private val useCase: GetMoviesUseCase,
    private val mapper: MovieListDomainToPresentationMapper
): BaseViewModel<MoviesListViewState>(MoviesListViewState()) {


    fun setup(email: String) {
        updateViewState { setupEmail(email) }
    }

    fun getMovies(){
        updateViewState {
            onLoading()
        }
        viewModelScope.launch {
            val response = useCase()

            when (response){
                is NetworkResult.Error -> updateViewState{
                    onError()
                }
                is NetworkResult.Exception -> updateViewState{onError()}
                is NetworkResult.Success -> updateViewState { onResult(mapper.toPresentation(response.data)) }
            }
        }
    }

}

data class MoviesListViewState(
    val isLoading: Boolean = false,
    val email: String = "",
    val onResult: List<MoviePresentationModel>? = null,
    val onError: Boolean = false
) {

    fun setupEmail(email: String) = copy(email = email)

    fun onLoading() = copy(isLoading = true)

    fun onResult(list: List<MoviePresentationModel>) = copy(onResult = list, isLoading = false, onError = false)

    fun onError() = copy(isLoading = false, onError = true)
}