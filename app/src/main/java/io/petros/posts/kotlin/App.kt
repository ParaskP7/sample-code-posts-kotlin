package io.petros.posts.kotlin

import android.app.Application
import android.support.v7.app.AppCompatDelegate
import com.github.salomonbrys.kodein.*
import io.petros.posts.kotlin.activity.main.viewmodel.PostsViewModel

import timber.log.Timber

class App : Application(), KodeinAware {

    companion object {
        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true) // NOTE: This enables the "proxy" trick on the vector images.
        }
    }

    override val kodein by Kodein.lazy {
        bind<PostsViewModel>() with instance(PostsViewModel())
    }

    override fun onCreate() {
        super.onCreate()
        initTimber()
        Timber.i("%s application created!", getString(R.string.app_name))
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

}
