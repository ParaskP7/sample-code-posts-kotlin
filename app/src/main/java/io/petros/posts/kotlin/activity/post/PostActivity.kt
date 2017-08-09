package io.petros.posts.kotlin.activity.post

import android.os.Bundle
import android.view.MenuItem
import io.petros.posts.kotlin.R
import io.petros.posts.kotlin.activity.BaseActivity

class PostActivity : BaseActivity() {

    // LIFECYCLE // ************************************************************************************************************************

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbarWithActionBar()
    }

    override fun getLayoutId(): Int = R.layout.activity_post

    // MENU ITEMS // ***********************************************************************************************************************

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        if (itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
