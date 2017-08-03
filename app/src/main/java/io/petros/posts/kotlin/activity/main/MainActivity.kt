package io.petros.posts.kotlin.activity.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import io.petros.posts.kotlin.R
import io.petros.posts.kotlin.activity.BaseActivity
import io.petros.posts.kotlin.extension.snack
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    // LIFECYCLE // ************************************************************************************************************************

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragment(R.id.fragment)
        setFab()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    private fun setFab() {
        fab.setOnClickListener(View::snack)
    }

    // MENU // *****************************************************************************************************************************

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_settings -> true
        else -> super.onOptionsItemSelected(item)
    }

}
