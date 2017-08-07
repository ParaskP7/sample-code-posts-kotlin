package io.petros.posts.kotlin.activity.main.viewmodel

import android.arch.lifecycle.LiveData
import com.github.salomonbrys.kodein.instance
import io.petros.posts.kotlin.activity.viewmodel.KodeinViewModel
import io.petros.posts.kotlin.model.Post
import io.petros.posts.kotlin.repository.PostsRepository
import timber.log.Timber

class PostsViewModel : KodeinViewModel() {

    private var _posts: LiveData<List<Post>>? = null
    val posts: LiveData<List<Post>> get() {
        if (_posts == null) {
            _posts = postsRepository.init()
        }
        return _posts ?: throw AssertionError("The live data for list of posts has not been initialised")
    }

    private val postsRepository: PostsRepository by injector.instance()

    fun loadPosts() {
        Timber.i("Loading posts...")
        postsRepository.loadPosts()
    }

}
