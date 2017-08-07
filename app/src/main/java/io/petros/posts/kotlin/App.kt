package io.petros.posts.kotlin

import android.app.Application
import android.support.v7.app.AppCompatDelegate
import com.github.salomonbrys.kodein.*
import io.petros.posts.kotlin.activity.main.viewmodel.PostsAdapter
import io.petros.posts.kotlin.app.constructPostsAdapter
import io.petros.posts.kotlin.app.constructRetrofitService
import io.petros.posts.kotlin.app.constructRxSchedulers
import io.petros.posts.kotlin.repository.PostsRepository
import io.petros.posts.kotlin.service.retrofit.RetrofitService
import io.petros.posts.kotlin.util.rx.RxSchedulers
import timber.log.Timber

class App : Application(), KodeinAware {

    companion object {
        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true) // NOTE: This enables the "proxy" trick on the vector images.
        }
    }

    // DEPENDENCY INJECTION // *************************************************************************************************************

    override val kodein by Kodein.lazy {
        bind<RxSchedulers>() with instance(constructRxSchedulers())
        bind<RetrofitService>() with instance(constructRetrofitService(applicationContext))
        bind<PostsAdapter>() with instance(constructPostsAdapter(applicationContext))
        bind<PostsRepository>() with singleton { PostsRepository(kodein) }
    }

    // LIFECYCLE // ************************************************************************************************************************

    override fun onCreate() {
        super.onCreate()
        initTimber()
        Timber.i("%s application created!", getString(R.string.app_name))
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

}
