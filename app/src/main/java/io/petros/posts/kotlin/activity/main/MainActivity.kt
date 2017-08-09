package io.petros.posts.kotlin.activity.main

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import butterknife.OnClick
import io.petros.posts.kotlin.R
import io.petros.posts.kotlin.activity.BaseActivity
import io.petros.posts.kotlin.activity.main.viewmodel.MainViewModel
import io.petros.posts.kotlin.databinding.ActivityMainBinding
import io.petros.posts.kotlin.extension.snack

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    // CONTRACT // *************************************************************************************************************************

    override fun constructBinding(): ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    override fun constructViewModel(): MainViewModel = ViewModelProviders.of(this)
            .get(MainViewModel::class.java)

    // LIFECYCLE // ************************************************************************************************************************

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbar()
        addFragment(R.id.fragment)
    }

    override fun onPause() {
        removeFragment(R.id.fragment)
        super.onPause()
    }

    // CLICK EVENTS // *********************************************************************************************************************

    @OnClick(R.id.fab)
    internal fun onFabClick() {
        binding.fab.snack(R.string.snackbar_text__pull_down_to_refresh)
    }

}
