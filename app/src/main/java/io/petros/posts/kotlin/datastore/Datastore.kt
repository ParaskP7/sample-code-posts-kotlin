package io.petros.posts.kotlin.datastore

import android.arch.lifecycle.LiveData
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance
import io.petros.posts.kotlin.datastore.db.PostDao
import io.petros.posts.kotlin.datastore.db.UserDao
import io.petros.posts.kotlin.model.Post
import io.petros.posts.kotlin.model.User
import io.petros.posts.kotlin.util.rx.RxSchedulers
import io.reactivex.Single
import timber.log.Timber

class Datastore(override val kodein: Kodein) : KodeinAware {

    private val rxSchedulers: RxSchedulers = instance()

    private val userDao: UserDao = instance()
    private val postDao: PostDao = instance()

    fun getUser(id: String): Single<User> {
        Timber.v("Getting user... [Id: $id]")
        return userDao.get(id)
    }

    fun saveUsers(users: List<User>): Boolean {
        if (!users.isEmpty()) {
            Single.fromCallable {
                Timber.v("Saving users... [$users]")
                userDao.save(users)
            }.observeOn(rxSchedulers.androidMainThreadScheduler)
                    .subscribeOn(rxSchedulers.ioScheduler)
                    .subscribe()
            return true
        } else return false
    }

    fun getPosts(): LiveData<List<Post>> {
        Timber.v("Getting posts...")
        return postDao.getAll()
    }

    fun savePosts(posts: List<Post>): Boolean {
        if (!posts.isEmpty()) {
            Single.fromCallable {
                Timber.v("Saving posts... [$posts]")
                postDao.save(posts)
            }.observeOn(rxSchedulers.androidMainThreadScheduler)
                    .subscribeOn(rxSchedulers.ioScheduler)
                    .subscribe()
            return true
        } else return false
    }

}
