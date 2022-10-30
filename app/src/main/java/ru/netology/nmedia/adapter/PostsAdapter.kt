package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.Post
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding

typealias OnLikeListener = (post: Post) -> Unit
typealias OnShareListener = (post: Post) -> Unit

class PostsAdapter (
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener,
) : ListAdapter<Post, PostViewHolder>(PostItemCallback()) {
   // var list = emptyList<Post>()
    //set(value) {
    ///    field = value
     //   notifyDataSetChanged()
    //}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onLikeListener, onShareListener)

    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
   // override fun getItemCount(): Int = list.size
}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener
    ) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content

            imageLikes.setImageResource(
                if (post.likedByMe)
                    R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
            )

            imageLikes.setOnClickListener {
                onLikeListener(post)
            }

            imageShare.setOnClickListener {
                onShareListener(post)
            }
        }

    }
}
     class PostItemCallback : DiffUtil.ItemCallback<Post>() {
         override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
             oldItem.id == newItem.id

         override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
            newItem == oldItem
     }




