package io.petros.posts.kotlin.activity.posts.fragment.viewmodel

import android.arch.lifecycle.LiveData
import com.github.salomonbrys.kodein.instance
import io.petros.posts.kotlin.activity.BaseViewModel
import io.petros.posts.kotlin.model.Post
import io.petros.posts.kotlin.repository.PostsRepository
import timber.log.Timber

class PostsViewModel : BaseViewModel() {

    private var _posts: LiveData<List<Post>>? = null
    val posts: LiveData<List<Post>> get() {
        if (_posts == null) {
            _posts = postsRepository.getPosts()
        }
        return _posts ?: throw AssertionError("The live data for list of posts is not initialised")
    }

    private val _postsRepository: PostsRepository by injector.instance()
    val postsRepository: PostsRepository get() {
        _postsRepository.init(lifecycle)
        return _postsRepository
    }

    fun loadPosts() {
        Timber.i("Loading posts...")
        postsRepository.loadPosts()
    }

}
