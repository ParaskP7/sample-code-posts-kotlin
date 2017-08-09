package io.petros.posts.kotlin.activity

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.ViewModel
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinInjector

open class BaseViewModel : ViewModel() {

    val injector = KodeinInjector()
    lateinit var lifecycle: Lifecycle

    fun whenReady(kodein: Kodein, lifecycleOwner: LifecycleOwner) {
        injector.inject(kodein)
        lifecycle = lifecycleOwner.lifecycle
    }

}
