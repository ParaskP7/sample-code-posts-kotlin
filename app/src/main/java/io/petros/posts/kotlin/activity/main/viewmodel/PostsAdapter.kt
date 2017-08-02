package io.petros.posts.kotlin.activity.main.viewmodel

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import io.petros.posts.kotlin.R
import io.petros.posts.kotlin.extension.inflate
import io.petros.posts.kotlin.model.Post
import kotlinx.android.synthetic.main.item_view_post.view.*

class PostsAdapter(val listener: (Post) -> Unit) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    val allPosts = ArrayList<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent.inflate(R.layout.item_view_post))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(allPosts[position], listener)

    override fun getItemCount(): Int = allPosts.size

    fun reloadAdapter(posts: List<Post>) {
        allPosts.clear()
        allPosts.addAll(posts)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: Post, listener: (Post) -> Unit) = with(itemView) {
            postIdTextView.text = post.id.toString()
            postTitleTextView.text = post.title
            setOnClickListener { listener(post) }
        }
    }

}
