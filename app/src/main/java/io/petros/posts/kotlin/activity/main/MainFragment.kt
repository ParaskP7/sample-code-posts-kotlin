package io.petros.posts.kotlin.activity.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.salomonbrys.kodein.instance
import io.petros.posts.kotlin.R
import io.petros.posts.kotlin.activity.BaseFragment
import io.petros.posts.kotlin.activity.main.viewmodel.PostsAdapter
import io.petros.posts.kotlin.activity.main.viewmodel.PostsViewModel
import io.petros.posts.kotlin.databinding.FragmentMainBinding
import io.petros.posts.kotlin.model.Post

class MainFragment : BaseFragment<FragmentMainBinding, PostsViewModel>(), SwipeRefreshLayout.OnRefreshListener {

    private val postsAdapter: PostsAdapter by injector.instance()

    // CONTRACT // *************************************************************************************************************************

    override fun constructBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMainBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

    override fun constructViewModel(): PostsViewModel = ViewModelProviders.of(this)
            .get(PostsViewModel::class.java)

    // LIFECYCLE // ************************************************************************************************************************

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setSwipeRefreshLayout()
        setRecyclerView()
        subscribeToPosts()
    }

    private fun setSwipeRefreshLayout() {
        binding.swipeRefreshLayout.setOnRefreshListener(this)
    }

    private fun setRecyclerView() {
        binding.postsRecyclerView.adapter = postsAdapter
    }

    private fun subscribeToPosts() {
        viewModel.posts.observe(this, Observer<List<Post>> {
            postsAdapter.setData(it!!)
            binding.swipeRefreshLayout.isRefreshing = false
        })
    }

    override fun onRefresh() {
        binding.swipeRefreshLayout.isRefreshing = true
        viewModel.loadPosts()
    }

}
