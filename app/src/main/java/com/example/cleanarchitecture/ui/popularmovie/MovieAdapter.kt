package com.example.cleanarchitecture.ui.popularmovie

import androidx.recyclerview.widget.DiffUtil
import com.bt.presentation.base.ui.BaseRecyclerAdapter
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.databinding.ItemMovieBinding
import com.example.cleanarchitecture.model.MovieItem

class MovieAdapter(
    private val listener: ((MovieItem?) -> Unit)? = null
) : BaseRecyclerAdapter<MovieItem, ItemMovieBinding>(
    object : DiffUtil.ItemCallback<MovieItem>() {
        override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem.id == newItem.id
        }
    }
) {
    override fun getLayoutRes(viewType: Int): Int = R.layout.item_movie

    override fun bindFirstTime(binding: ItemMovieBinding) {
        binding.apply {
            root.setOnClickListener {
                item?.let {
                    listener?.invoke(it)
                }
            }
        }
    }
}
