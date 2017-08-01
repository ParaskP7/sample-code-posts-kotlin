package io.petros.posts.kotlin.activity.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import io.petros.posts.kotlin.R
import io.petros.posts.kotlin.extension.inflate
import io.petros.posts.kotlin.model.Post
import kotlinx.android.synthetic.main.item_view_post.view.*

class PostsAdapter(val posts: List<Post>, val listener: (Post) -> Unit) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent.inflate(R.layout.item_view_post))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(posts[position], listener)

    override fun getItemCount(): Int = posts.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: Post, listener: (Post) -> Unit) = with(itemView) {
            postIdTextView.text = post.id.toString()
            postTitleTextView.text = post.title
            setOnClickListener { listener(post) }
        }
    }

}
