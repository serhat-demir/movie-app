package com.serhatd.movieapp.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.serhatd.movieapp.R
import com.serhatd.movieapp.data.entity.Movie
import com.serhatd.movieapp.databinding.CardMovieBinding
import com.squareup.picasso.Picasso

class MovieAdapter(private val movies: List<Movie>): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    class MovieViewHolder(val binding: CardMovieBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding: CardMovieBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.card_movie, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binding.movieAdapter = this
        holder.binding.movie = movies[position]

        Picasso.get().load(movies[position].movie_image).into(holder.binding.cardMovieImgPoster)
    }

    fun navToWatch(view: View, movie: Movie) {
        val moviesToWatch = MoviesFragmentDirections.moviesToWatch(movie)
        Navigation.findNavController(view).navigate(moviesToWatch)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}