package io.petros.posts.kotlin.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance
import io.petros.posts.kotlin.model.Post
import io.petros.posts.kotlin.model.User
import io.petros.posts.kotlin.service.retrofit.RetrofitService
import io.petros.posts.kotlin.util.rx.RxSchedulers
import timber.log.Timber

class PostsRepository(override val kodein: Kodein) : KodeinAware {

    private val rxSchedulers: RxSchedulers = instance()
    private val retrofitService: RetrofitService = instance()

    private val data = MutableLiveData<List<Post>>()

    fun init(): LiveData<List<Post>> {
        Timber.d("Init posts repository...")
        return data
    }

    fun loadPosts() {
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
        data.value = retrievedPosts
    }

    private fun handlePostsError(throwable: Throwable) {
        Timber.w(throwable, "Error while loading posts...")
    }

}
