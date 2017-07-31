package io.petros.posts.kotlin

import android.app.Application
import android.support.v7.app.AppCompatDelegate

import timber.log.Timber

class App : Application() {

    companion object {
        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true) // NOTE: This enables the "proxy" trick on the vector images.
        }
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
