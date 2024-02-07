package com.example.movieapp.presentation.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<VIEW_STATE : Any>(
    private val initialState: VIEW_STATE
) : ViewModel() {
    private val _viewState: MutableStateFlow<VIEW_STATE> =
        MutableStateFlow(this.initialState)
    val viewState = _viewState.asLiveData()

    private val currentViewState: VIEW_STATE get() = viewState.value ?: initialState

    internal fun <T> LiveData<T>.asLiveData() = this

    internal fun updateViewState(newViewState: VIEW_STATE) {
        _viewState.update { newViewState }
    }

    internal fun updateViewState(updatedState: VIEW_STATE.() -> VIEW_STATE) =
        updateViewState(currentViewState.updatedState())
}