package io.petros.posts.kotlin.activity.main

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import io.petros.posts.kotlin.R
import io.petros.posts.kotlin.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    // LIFECYCLE // ************************************************************************************************************************

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFab()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    private fun setFab() {
        fab.setOnClickListener {
            Snackbar.make(it, R.string.snackbar_text, Snackbar.LENGTH_LONG)
                    .setAction(R.string.snackbar_action_text, {})
                    .show()
        }
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
