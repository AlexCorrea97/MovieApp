package com.example.movieapp.domain.use_case

import com.example.movieapp.domain.repository.GetMovieDetailRespository

class GetMovieDetailUseCase(val repository: GetMovieDetailRespository) {

    suspend operator fun invoke(id: Long) = repository.getMovieDetail(id)
}