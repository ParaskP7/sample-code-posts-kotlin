package io.petros.posts.kotlin.activity.viewmodel

import android.arch.lifecycle.ViewModel
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinInjector

open class KodeinViewModel : ViewModel() {

    val injector = KodeinInjector()

    fun whenReady(kodein: Kodein) = injector.inject(kodein)

}
