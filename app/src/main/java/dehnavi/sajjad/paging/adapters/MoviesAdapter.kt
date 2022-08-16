package dehnavi.sajjad.paging.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.DifferCallback
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import dehnavi.sajjad.paging.databinding.ItemMovieBinding
import dehnavi.sajjad.paging.model.Data
import javax.inject.Inject

class MoviesAdapter @Inject constructor() : PagingDataAdapter<Data, MoviesAdapter.ViewHolder>(
    differCallback
) {
    //binding
    private lateinit var binding: ItemMovieBinding

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        fun setData(item: Data) {
            binding.apply {
                imgPoster.load(item.poster) {
                    crossfade(true)
                    crossfade(500)
                }
                titleMovie.text = item.title
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(getItem(position)!!)
        holder.setIsRecyclable(false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }


    companion object {
        val differCallback = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return newItem == oldItem
            }

        }
    }
}