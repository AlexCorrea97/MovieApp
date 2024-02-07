package com.example.movieapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.example.movieapp.BuildConfig
import com.example.movieapp.R
import com.example.movieapp.databinding.ItemMovieBinding
import com.example.movieapp.presentation.model.MoviePresentationModel

class MovieItemAdapter(
    val movieList: List<MoviePresentationModel>,
    val onSelect: (movie: MoviePresentationModel) -> Unit,
) : RecyclerView.Adapter<MovieItemAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position], onSelect)
    }

    override fun getItemCount(): Int = movieList.size


    class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(movie: MoviePresentationModel, onSelect: (movie: MoviePresentationModel) -> Unit) {
            val context = binding.root.context
            binding.titleTextView.text = movie.title
            binding.rateextView.text =
                context.getString(R.string.calificacion_value, movie.rate.toString())

            val token = BuildConfig.API_KEY
            val url = BuildConfig.IMAGES_BASE_URL + movie.image
            val glideUrl = GlideUrl(url) { mapOf(Pair("Authorization", "Bearer $token")) }

            Glide.with(context)
                .load(glideUrl)
                .into(binding.image)

            binding.cardview.setOnClickListener {
                onSelect(movie)
            }
        }
    }
}