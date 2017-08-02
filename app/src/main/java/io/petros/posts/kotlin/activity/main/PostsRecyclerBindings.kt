package io.petros.posts.kotlin.activity.main

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import io.petros.posts.kotlin.model.Post

@BindingAdapter("android:items")
fun setPostsBinding(postsRecyclerView: RecyclerView, posts: List<Post>) {
    val adapter = postsRecyclerView.adapter
    if (adapter is PostsAdapter) {
        adapter.reloadAdapter(posts)
    }
}
