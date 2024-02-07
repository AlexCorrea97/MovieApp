package com.example.movieapp.domain.use_case

import com.example.movieapp.domain.repository.GetMoviesRepository

class GetMoviesUseCase(
    private val repository: GetMoviesRepository
) {
    suspend operator fun invoke() = repository.getMovies()
}