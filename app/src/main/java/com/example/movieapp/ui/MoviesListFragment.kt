package com.example.movieapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMoviesListBinding
import com.example.movieapp.presentation.viewmodel.MoviesListViewModel
import com.example.movieapp.presentation.model.MoviePresentationModel
import com.example.movieapp.ui.adapter.MovieItemAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class MoviesListFragment : Fragment() {
    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<MoviesListViewModel>()

    private lateinit var adapter: MovieItemAdapter

    private val email: String by lazy {
        requireNotNull(
            requireArguments().getString(EMAIL_KEY)
        ) {
            "A EMAIL MUST BE PROVIDED"
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observers()
        viewModel.setup(email)
        viewModel.getMovies()
    }


    private fun setupRecycler(list: List<MoviePresentationModel>) = with(binding) {
        adapter = MovieItemAdapter(movieList = list) {
            findNavController().navigate(
                R.id.action_moviesListFragment_to_movieDetailFragment, bundleOf(
                    MovieDetailFragment.MOVIE_ID_KEY to it.id
                )
            )
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.itemAnimator = null
        recyclerView.adapter = adapter
    }

    private fun observers() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            it.onResult?.let {
                setupRecycler(it)
            }
            binding.recyclerView.visibility = if (it.isLoading) View.GONE else View.VISIBLE
            binding.progressbar.visibility = if (it.isLoading) View.VISIBLE else View.GONE

            binding.emailTextview.text = it.email.ifBlank { email }
        }
    }

    companion object{
        const val EMAIL_KEY = "EMAIL_KEY"
    }
}