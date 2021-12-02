package com.buildappswithalejing.condorlabs_skill_test.views

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.buildappswithalejing.condorlabs_skill_test.databinding.ListViewItemBinding
import com.buildappswithalejing.condorlabs_skill_test.network.Movie
import com.buildappswithalejing.condorlabs_skill_test.network.MoviesData

class MoviesListAdapter(private val onClickListener: OnClickListener) :
            ListAdapter<Movie,
            MoviesListAdapter.MoviesDataViewHolder>(DiffCallback) {

    class MoviesDataViewHolder(private var binding: ListViewItemBinding):
                                RecyclerView.ViewHolder(binding.root) {

        fun bind(Movie: Movie) {
            binding.movie = Movie
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesDataViewHolder {
        return MoviesDataViewHolder(ListViewItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MoviesDataViewHolder, position: Int) {
        val movieData = getItem(position)
        // Log.d("MoviesListAdapter", movieData.id.toString())
        holder.bind(movieData)
        holder.itemView.setOnClickListener {
            //Log.d("MoviesListAdapter", movieData.title)
            onClickListener.onClick(movieData.title)
        }
    }

    class OnClickListener(val clickListener: (id: String) -> Unit) {
        fun onClick(id: String) = clickListener(id)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.posterPath == newItem.posterPath
        }
    }
}