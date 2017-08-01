package io.petros.posts.kotlin.activity.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import io.petros.posts.kotlin.R
import io.petros.posts.kotlin.activity.BaseFragment
import io.petros.posts.kotlin.databinding.FragmentMainBinding
import io.petros.posts.kotlin.model.Post

class MainFragment : BaseFragment() {

    // LIFECYCLE // ************************************************************************************************************************

    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindPost()
    }

    private fun bindPost() {
        val binding: FragmentMainBinding = DataBindingUtil.setContentView(activity, R.layout.fragment_main)
        binding.post = Post(1, 1, "Title", "Body")
    }

}
