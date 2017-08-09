package io.petros.posts.kotlin.activity.main.fragment

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
import io.petros.posts.kotlin.activity.main.fragment.adapter.OnPostItemViewClickListener
import io.petros.posts.kotlin.activity.main.fragment.adapter.PostsAdapter
import io.petros.posts.kotlin.activity.main.fragment.viewmodel.PostsViewModel
import io.petros.posts.kotlin.databinding.FragmentPostsBinding
import io.petros.posts.kotlin.model.Post

class PostsFragment : BaseFragment<FragmentPostsBinding, PostsViewModel>(), SwipeRefreshLayout.OnRefreshListener {

    private val postsAdapter: PostsAdapter by injector.instance()

    // CONTRACT // *************************************************************************************************************************

    override fun constructBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentPostsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_posts, container, false)

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
        postsAdapter.listener = OnPostItemViewClickListener(activity)
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
