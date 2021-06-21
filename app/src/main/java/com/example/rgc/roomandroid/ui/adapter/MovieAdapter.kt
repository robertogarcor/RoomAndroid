package com.example.rgc.roomandroid.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rgc.roomandroid.R
import com.example.rgc.roomandroid.ui.custom.AspectRatioImageView
import com.example.rgc.roomandroid.data.domain.model.Movie
import com.example.rgc.roomandroid.ui.MovieDetailActivity

class MovieAdapter(private val context: Context) :
    ListAdapter<Movie, MovieAdapter.ItemViewholder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        return ItemViewholder(view)
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) {
        val item_movie = getItem(position)
        holder.bind(context, item_movie)
    }


    class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title_movie : TextView = itemView.findViewById<TextView>(R.id.textView_title_movie)
        private val image_movie : AspectRatioImageView = itemView.findViewById<AspectRatioImageView>(R.id.imageView_movie)

        fun bind(context: Context, movie: Movie) {
            title_movie.text = movie.title
            image_movie.ratio = 1.5f
            Glide.with(context).load(movie.enclosure).into(image_movie);
            // Listener item click
            itemView.setOnClickListener(View.OnClickListener {
                //Show detail item movie
                val intent = Intent(context, MovieDetailActivity::class.java).apply {
                    putExtra("MOVIE", movie)
                }
                context.startActivity(intent)
            })
        }
    }
}

class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}

