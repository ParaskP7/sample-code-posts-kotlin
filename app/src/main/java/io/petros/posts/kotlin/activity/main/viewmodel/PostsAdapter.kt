package io.petros.posts.kotlin.activity.main.viewmodel

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import io.petros.posts.kotlin.R
import io.petros.posts.kotlin.extension.inflate
import io.petros.posts.kotlin.model.Post

class PostsAdapter(override val kodein: Kodein, val listener: (Post) -> Unit) : KodeinAware, RecyclerView.Adapter<PostViewHolder>() {

    val allPosts = ArrayList<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder = PostViewHolder(kodein, parent.inflate(R.layout.item_view_post))

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) = holder.bind(allPosts[position], listener)

    override fun getItemCount(): Int = allPosts.size

    fun setData(posts: List<Post>) {
        allPosts.clear()
        allPosts.addAll(posts)
        notifyDataSetChanged()
    }

}
