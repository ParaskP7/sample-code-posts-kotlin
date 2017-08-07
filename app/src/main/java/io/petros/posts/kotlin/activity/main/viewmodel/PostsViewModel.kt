package io.petros.posts.kotlin.activity.main.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.github.salomonbrys.kodein.instance
import io.petros.posts.kotlin.activity.viewmodel.KodeinViewModel
import io.petros.posts.kotlin.model.Post
import io.petros.posts.kotlin.model.User
import io.petros.posts.kotlin.service.retrofit.RetrofitService
import io.petros.posts.kotlin.util.rx.RxSchedulers
import timber.log.Timber

class PostsViewModel : KodeinViewModel() {

    var posts = MutableLiveData<List<Post>>()

    private val rxSchedulers: RxSchedulers by injector.instance()
    private val retrofitService: RetrofitService by injector.instance()

    fun loadPosts() {
        Timber.i("Loading posts...")
        Timber.d("Retrieving users...")
        retrofitService.users()
                .observeOn(rxSchedulers.androidMainThreadScheduler)
                .subscribeOn(rxSchedulers.ioScheduler)
                .subscribe(this::handleUsersResponse, this::handleUsersError)
    }

    private fun handleUsersResponse(retrievedUsers: List<User>) {
        Timber.v("Users was successfully retrieved... [$retrievedUsers]")
        Timber.d("Retrieving posts...")
        retrofitService.posts()
                .observeOn(rxSchedulers.androidMainThreadScheduler)
                .subscribeOn(rxSchedulers.ioScheduler)
                .subscribe(this::handlePostsResponse, this::handlePostsError)
    }

    private fun handleUsersError(throwable: Throwable) {
        Timber.w(throwable, "Error while loading users...")
    }

    private fun handlePostsResponse(retrievedPosts: List<Post>) {
        Timber.v("Posts was successfully retrieved... [$retrievedPosts]")
        posts.value = retrievedPosts
    }

    private fun handlePostsError(throwable: Throwable) {
        Timber.w(throwable, "Error while loading posts...")
    }

}
