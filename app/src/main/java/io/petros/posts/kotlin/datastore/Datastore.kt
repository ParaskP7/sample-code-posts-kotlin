package io.petros.posts.kotlin.datastore

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance
import io.petros.posts.kotlin.datastore.db.UserDao
import io.petros.posts.kotlin.model.User
import io.petros.posts.kotlin.util.rx.RxSchedulers
import io.reactivex.Single
import timber.log.Timber

class Datastore(override val kodein: Kodein) : KodeinAware {

    private val rxSchedulers: RxSchedulers = instance()

    private val userDao: UserDao = instance()

    fun saveUsers(users: List<User>): Boolean {
        if (!users.isEmpty()) {
            Single.fromCallable {
                Timber.d("Saving users... [$users]")
                userDao.save(users)
            }.observeOn(rxSchedulers.androidMainThreadScheduler)
                    .subscribeOn(rxSchedulers.ioScheduler)
                    .subscribe()
            return true
        } else return false
    }

}
