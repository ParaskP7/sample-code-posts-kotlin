package io.petros.posts.kotlin.activity.posts

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import butterknife.OnClick
import io.petros.posts.kotlin.R
import io.petros.posts.kotlin.activity.BaseActivity
import io.petros.posts.kotlin.activity.posts.viewmodel.MainViewModel
import io.petros.posts.kotlin.databinding.ActivityPostsBinding
import io.petros.posts.kotlin.extension.snack

class PostsActivity : BaseActivity<ActivityPostsBinding, MainViewModel>() {

    // CONTRACT // *************************************************************************************************************************

    override fun constructBinding(): ActivityPostsBinding = DataBindingUtil.setContentView(this, R.layout.activity_posts)

    override fun constructViewModel(): MainViewModel = ViewModelProviders.of(this)
            .get(MainViewModel::class.java)

    // LIFECYCLE // ************************************************************************************************************************

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbar(binding.toolbar)
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
