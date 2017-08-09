package io.petros.posts.kotlin.activity.post

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.MenuItem
import io.petros.posts.kotlin.R
import io.petros.posts.kotlin.activity.BaseActivity
import io.petros.posts.kotlin.activity.main.PostViewModel
import io.petros.posts.kotlin.databinding.ActivityPostBinding

class PostActivity : BaseActivity<ActivityPostBinding, PostViewModel>() {

    // CONTRACT // *************************************************************************************************************************

    override fun constructBinding(): ActivityPostBinding = DataBindingUtil.setContentView(this, R.layout.activity_post)

    override fun constructViewModel(): PostViewModel = ViewModelProviders.of(this)
            .get(PostViewModel::class.java)

    // LIFECYCLE // ************************************************************************************************************************

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbarWithActionBar(binding.toolbar)
    }

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
