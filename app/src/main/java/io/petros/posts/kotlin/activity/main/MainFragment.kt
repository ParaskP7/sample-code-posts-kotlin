package io.petros.posts.kotlin.activity.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import io.petros.posts.kotlin.R
import io.petros.posts.kotlin.activity.BaseFragment
import io.petros.posts.kotlin.databinding.FragmentMainBinding
import io.petros.posts.kotlin.extension.toast

class MainFragment : BaseFragment() {

    lateinit var binding: FragmentMainBinding
    val postsViewModel = PostsViewModel()

    val postsAdapter = PostsAdapter {
        toast("${it.title} Clicked")
    }

    // LIFECYCLE // ************************************************************************************************************************

    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBinding()
    }

    private fun setBinding() {
        binding = DataBindingUtil.setContentView(activity, R.layout.fragment_main)
        binding.postsViewModel = postsViewModel
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecyclerView()
    }

    private fun setRecyclerView() {
        binding.postsRecyclerView.adapter = postsAdapter
    }

    override fun onResume() {
        super.onResume()
        postsViewModel.loadPosts()
    }

}
