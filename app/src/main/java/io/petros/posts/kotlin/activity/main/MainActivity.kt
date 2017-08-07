package io.petros.posts.kotlin.activity.main

import android.os.Bundle
import butterknife.OnClick
import io.petros.posts.kotlin.R
import io.petros.posts.kotlin.activity.BaseActivity
import io.petros.posts.kotlin.extension.snack
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    // LIFECYCLE // ************************************************************************************************************************

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(R.id.fragment)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onPause() {
        removeFragment(R.id.fragment)
        super.onPause()
    }

    // CLICK EVENTS // *********************************************************************************************************************

    @OnClick(R.id.fab)
    internal fun onFabClick() {
        fab.snack(R.string.snackbar_text__pull_down_to_refresh)
    }

}
