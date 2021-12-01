package com.buildappswithalejing.condorlabs_skill_test.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.buildappswithalejing.condorlabs_skill_test.databinding.ListViewItemBinding
import com.buildappswithalejing.condorlabs_skill_test.network.MoviesData

class MoviesListAdapter : ListAdapter<MoviesData,
        MoviesListAdapter.MoviesDataViewHolder>(DiffCallback) {

    class MoviesDataViewHolder(private var binding: ListViewItemBinding):
                                RecyclerView.ViewHolder(binding.root) {

        fun bind(MoviesData: MoviesData) {
            //binding.photo = MoviesData
            //binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesDataViewHolder {
        return MoviesDataViewHolder(ListViewItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MoviesDataViewHolder, position: Int) {
        val moviesData = getItem(position)
        holder.bind(moviesData)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MoviesData>() {
        override fun areItemsTheSame(oldItem: MoviesData, newItem: MoviesData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MoviesData, newItem: MoviesData): Boolean {
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }
    }
}