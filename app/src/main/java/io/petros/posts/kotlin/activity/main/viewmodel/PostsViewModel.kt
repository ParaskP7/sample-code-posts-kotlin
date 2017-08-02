package io.petros.posts.kotlin.activity.main.viewmodel

import android.databinding.BaseObservable
import android.databinding.ObservableArrayList
import io.petros.posts.kotlin.activity.viewmodel.ViewModel
import io.petros.posts.kotlin.model.Post

class PostsViewModel : BaseObservable(), ViewModel {

    val posts = ObservableArrayList<Post>()

    fun loadPosts() {
        posts.clear()
        posts.addAll(listOf(Post(1, 1, "Title 1", "Body 1"), Post(2, 2, "Title 2", "Body 2")))
    }

}
