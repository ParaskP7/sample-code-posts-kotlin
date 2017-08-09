package io.petros.posts.kotlin.repository

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.OnLifecycleEvent
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance
import io.petros.posts.kotlin.datastore.Datastore
import io.petros.posts.kotlin.model.Post
import io.petros.posts.kotlin.model.User
import io.petros.posts.kotlin.service.retrofit.WebService
import io.petros.posts.kotlin.util.rx.RxSchedulers
import io.reactivex.disposables.Disposable
import timber.log.Timber

class PostsRepository(override val kodein: Kodein) : KodeinAware, LifecycleObserver {

    private var subscription: Disposable? = null
    private val rxSchedulers: RxSchedulers = instance()
    private val webService: WebService = instance()

    private val datastore: Datastore = instance()

    fun init(lifecycle: Lifecycle) {
        lifecycle.addObserver(this)
    }

    // LIFECYCLE // ************************************************************************************************************************

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onStop() {
        Timber.d("%s stopped.", javaClass.simpleName)
        if (subscription != null && subscription!!.isDisposed) {
            Timber.d("Unsubscribe from posts.")
            subscription!!.dispose()
        }
    }

    // REPOSITORY // ***********************************************************************************************************************

    fun getPosts(): LiveData<List<Post>> {
        Timber.d("Retrieving posts from db...")
        return datastore.getPosts()
    }

    fun loadPosts() {
        retrieveUsersFromWeb()
    }

    private fun retrieveUsersFromWeb() {
        Timber.d("Retrieving users from web...")
        subscription = webService.users()
                .observeOn(rxSchedulers.androidMainThreadScheduler)
                .subscribeOn(rxSchedulers.ioScheduler)
                .subscribe(this::handleUsersResponse, this::handleUsersError)
    }

    private fun handleUsersResponse(retrievedUsers: List<User>) {
        Timber.v("Users was successfully retrieved from web... [$retrievedUsers]")
        if (datastore.saveUsers(retrievedUsers)) {
            retrievePostsFromWeb()
        } else {
            Timber.w("No users available to save on db...")
        }
    }

    private fun handleUsersError(throwable: Throwable) {
        Timber.w(throwable, "Error while loading users from web...")
    }

    private fun retrievePostsFromWeb() {
        Timber.d("Retrieving posts from web...")
        subscription = webService.posts()
                .observeOn(rxSchedulers.androidMainThreadScheduler)
                .subscribeOn(rxSchedulers.ioScheduler)
                .subscribe(this::handlePostsResponse, this::handlePostsError)
    }

    private fun handlePostsResponse(retrievedPosts: List<Post>) {
        Timber.v("Posts was successfully retrieved from web... [$retrievedPosts]")
        if (!datastore.savePosts(retrievedPosts)) {
            Timber.w("No posts available to save on db...")
        }
    }

    private fun handlePostsError(throwable: Throwable) {
        Timber.w(throwable, "Error while loading posts from web...")
    }

}
