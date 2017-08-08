package io.petros.posts.kotlin.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance
import io.petros.posts.kotlin.datastore.Datastore
import io.petros.posts.kotlin.datastore.cache.PostsCache
import io.petros.posts.kotlin.model.Post
import io.petros.posts.kotlin.model.User
import io.petros.posts.kotlin.service.retrofit.WebService
import io.petros.posts.kotlin.util.rx.RxSchedulers
import timber.log.Timber

class PostsRepository(override val kodein: Kodein) : KodeinAware {

    private val rxSchedulers: RxSchedulers = instance()
    private val webService: WebService = instance()

    private val postsCache: PostsCache = instance()
    private val datastore: Datastore = instance()

    private val data = MutableLiveData<List<Post>>()

    fun init(): LiveData<List<Post>> {
        Timber.d("Init posts repository...")
        return data
    }

    fun loadPosts() {
        val cachedData = postsCache.get()
        if (cachedData != null) {
            Timber.d("Retrieving posts from cache...")
            data.value = cachedData
        } else {
            Timber.d("Retrieving users...")
            webService.users()
                    .observeOn(rxSchedulers.androidMainThreadScheduler)
                    .subscribeOn(rxSchedulers.ioScheduler)
                    .subscribe(this::handleUsersResponse, this::handleUsersError)
        }
    }

    private fun handleUsersResponse(retrievedUsers: List<User>) {
        Timber.v("Users was successfully retrieved... [$retrievedUsers]")
        if (datastore.saveUsers(retrievedUsers)) {
            Timber.d("Retrieving posts...")
            webService.posts()
                    .observeOn(rxSchedulers.androidMainThreadScheduler)
                    .subscribeOn(rxSchedulers.ioScheduler)
                    .subscribe(this::handlePostsResponse, this::handlePostsError)
        } else {
            Timber.w("Users are not available...")
        }
    }

    private fun handleUsersError(throwable: Throwable) {
        Timber.w(throwable, "Error while loading users...")
    }

    private fun handlePostsResponse(retrievedPosts: List<Post>) {
        Timber.v("Posts was successfully retrieved... [$retrievedPosts]")
        data.value = retrievedPosts
        postsCache.set(retrievedPosts)
    }

    private fun handlePostsError(throwable: Throwable) {
        Timber.w(throwable, "Error while loading posts...")
    }

}
