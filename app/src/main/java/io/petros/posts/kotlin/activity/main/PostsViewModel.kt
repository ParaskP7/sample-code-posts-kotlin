package io.petros.posts.kotlin.activity.main

import android.databinding.BaseObservable
import android.databinding.ObservableArrayList
import io.petros.posts.kotlin.model.Post

class PostsViewModel : BaseObservable() {

    val posts = ObservableArrayList<Post>()

    fun loadPosts() {
        posts.clear()
        posts.addAll(listOf(Post(1, 1, "Title 1", "Body 1"), Post(2, 2, "Title 2", "Body 2")))
    }

}
