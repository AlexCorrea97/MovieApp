package com.example.movieapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.example.movieapp.BuildConfig
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMovieDetailBinding
import com.example.movieapp.presentation.model.MovieDetailPresentationModel
import com.example.movieapp.presentation.viewmodel.MovieDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private val movieId: Long by lazy {
        requireNotNull(
            requireArguments().getLong(MOVIE_ID_KEY)
        ) {
            "A MOVIE ID MUST BE PROVIDED"
        }
    }

    private val viewModel by viewModel<MovieDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observers()
        viewModel.getMovieDetail(movieId)
    }

    private fun observers() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            if (it.isLoading) {
                binding.progressbar.visibility = View.VISIBLE
                binding.scrollview.visibility = View.GONE
            }
            else {
                binding.progressbar.visibility = View.GONE
                binding.scrollview.visibility = View.VISIBLE
            }

            it.onResult?.let {
                setupResult(it)
            }
        }
    }

    private fun setupResult(movie: MovieDetailPresentationModel) = with(binding) {
        val token = BuildConfig.API_KEY
        val url = BuildConfig.IMAGES_BASE_URL + movie.image
        val glideUrl = GlideUrl(url) { mapOf(Pair("Authorization", "Bearer $token")) }

        Glide.with(requireContext())
            .load(glideUrl)
            .into(imageView)

        titleTextView.text = movie.title
        overviewTextView.text = movie.overview
        durationTextView.text = "Minutes: ${movie.runTime}"
        releaseDateTextView.text = "Release date: ${movie.releaseDate}"
        rateTextView.text = "Classification: ${if (movie.isAdult) "Only adult" else "Family"}"
        var genders = ""
        movie.genderList.forEach {
            genders = genders + it + "\n"
        }
        gendersListTextView.text = genders
    }

    companion object {
        const val MOVIE_ID_KEY = "MOVIE_ID_KEY"
    }
}