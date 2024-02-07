package com.example.movieapp.app

import android.app.Application
import com.example.movieapp.data.mapper.MovieDataToDomainMapper
import com.example.movieapp.data.mapper.MovieDetailDataToDomainMapper
import com.example.movieapp.data.repository.GetMovieDetailRepositoryImp
import com.example.movieapp.data.repository.GetMoviesRepositoryImp
import com.example.movieapp.data.repository.LoginRepositoryImp
import com.example.movieapp.data.retrofit.networkModule
import com.example.movieapp.domain.use_case.GetMovieDetailUseCase
import com.example.movieapp.domain.use_case.GetMoviesUseCase
import com.example.movieapp.domain.use_case.LoginUseCase
import com.example.movieapp.domain.use_case.RegisterUserUseCase
import com.example.movieapp.presentation.mapper.MovieDetailDomainToPresentationMapper
import com.example.movieapp.presentation.viewmodel.MoviesListViewModel
import com.example.movieapp.presentation.mapper.MovieListDomainToPresentationMapper
import com.example.movieapp.presentation.viewmodel.LoginViewModel
import com.example.movieapp.presentation.viewmodel.MovieDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val appModule = module {
            single { MovieDataToDomainMapper() }
            factory { GetMoviesUseCase(GetMoviesRepositoryImp(get(), get())) }
            single { MovieListDomainToPresentationMapper() }
            factory { MovieDetailDataToDomainMapper() }
            single { GetMovieDetailUseCase(GetMovieDetailRepositoryImp(get(), get())) }
            viewModel { MoviesListViewModel(get(), get()) }
            single { MovieDetailDomainToPresentationMapper() }
            viewModel { MovieDetailViewModel(get(), get()) }

            single { RegisterUserUseCase(LoginRepositoryImp()) }
            single { LoginUseCase(LoginRepositoryImp()) }
            viewModel { LoginViewModel(get(), get()) }
        }
        startKoin {
            modules(
                appModule,
                networkModule
            )
        }
    }
}