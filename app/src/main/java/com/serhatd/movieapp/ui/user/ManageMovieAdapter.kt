package com.serhatd.movieapp.ui.user

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.serhatd.movieapp.R
import com.serhatd.movieapp.data.entity.Movie
import com.serhatd.movieapp.databinding.CardMovieManageBinding
import com.squareup.picasso.Picasso

class ManageMovieAdapter(private val context: Context, private val viewModel: UserViewModel, private val movies: List<Movie>): RecyclerView.Adapter<ManageMovieAdapter.ManageMovieViewHolder>() {
    class ManageMovieViewHolder(val binding: CardMovieManageBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManageMovieViewHolder {
        val binding: CardMovieManageBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.card_movie_manage, parent, false)
        return ManageMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ManageMovieViewHolder, position: Int) {
        holder.binding.manageMovieAdapter = this
        holder.binding.movie = movies[position]

        Picasso.get().load(movies[position].movie_image).into(holder.binding.cardMovieManageImgPoster)
    }

    fun delete(movie: Movie) {
        val alertDialog  = AlertDialog.Builder(context)
        alertDialog.setTitle(context.getString(R.string.dialog_delete_movie_title))
        alertDialog.setMessage(context.getString(R.string.dialog_delete_movie_msg))
        alertDialog.setPositiveButton(context.getString(R.string.btn_save)) { _, _ ->
            viewModel.deleteMovie(movie.movie_id)
        }
        alertDialog.setNegativeButton(context.getString(R.string.btn_cancel), null)
        alertDialog.create().show()
    }

    fun edit(movie: Movie) {
        val dialogMovieManage = LayoutInflater.from(context).inflate(R.layout.dialog_movie_manage, null)
        val txtMovieName = dialogMovieManage.findViewById<EditText>(R.id.dialogMovieManageTxtName)
        val txtMovieImage = dialogMovieManage.findViewById<EditText>(R.id.dialogMovieManageTxtImage)
        val txtMovieUrl = dialogMovieManage.findViewById<EditText>(R.id.dialogMovieManageTxtUrl)

        txtMovieName.setText(movie.movie_name)
        txtMovieImage.setText(movie.movie_image)
        txtMovieUrl.setText(movie.movie_url)

        val alertDialog  = AlertDialog.Builder(context)
        alertDialog.setTitle(context.getString(R.string.dialog_movie_manage_title))
        alertDialog.setView(dialogMovieManage)
        alertDialog.setPositiveButton(context.getString(R.string.btn_save)) { _, _ ->
            viewModel.editMovie(movie.movie_id, txtMovieName.text.toString().trim(), txtMovieImage.text.toString().trim(), txtMovieUrl.text.toString().trim())
        }
        alertDialog.setNegativeButton(context.getString(R.string.btn_cancel), null)
        alertDialog.create().show()
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}