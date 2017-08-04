package io.petros.posts.kotlin.activity.main.viewmodel

import android.databinding.BaseObservable
import android.databinding.ObservableArrayList
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance
import io.petros.posts.kotlin.activity.viewmodel.ViewModel
import io.petros.posts.kotlin.model.Post
import io.petros.posts.kotlin.model.User
import io.petros.posts.kotlin.service.retrofit.RetrofitService
import io.petros.posts.kotlin.util.rx.RxSchedulers
import timber.log.Timber

class PostsViewModel(override val kodein: Kodein) : BaseObservable(), ViewModel, KodeinAware {

    val posts = ObservableArrayList<Post>()

    private val rxSchedulers: RxSchedulers = instance()
    private val retrofitService: RetrofitService = instance()

    fun loadPosts() {
        Timber.i("Loading users...")
        retrofitService.users()
                .observeOn(rxSchedulers.androidMainThreadScheduler)
                .subscribeOn(rxSchedulers.ioScheduler)
                .subscribe(this::handleUsersResponse, this::handleUsersError)
    }

    private fun handleUsersResponse(retrievedUsers: List<User>) {
        Timber.v("Users was successfully retrieved... [$retrievedUsers]")
        Timber.i("Loading posts...")
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
        posts.clear()
        posts.addAll(retrievedPosts)
    }

    private fun handlePostsError(throwable: Throwable) {
        Timber.w(throwable, "Error while loading posts...")
    }

}
