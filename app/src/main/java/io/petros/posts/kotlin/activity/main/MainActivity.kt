package io.petros.posts.kotlin.activity.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import butterknife.OnClick
import io.petros.posts.kotlin.R
import io.petros.posts.kotlin.activity.BaseActivity
import io.petros.posts.kotlin.extension.snack
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    // LIFECYCLE // ************************************************************************************************************************

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragment(R.id.fragment)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
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

    // CLICK EVENTS // *********************************************************************************************************************

    @OnClick(R.id.fab)
    internal fun onFabClick() {
        fab.snack()
    }

}
