package io.petros.posts.kotlin.activity.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.github.salomonbrys.kodein.instance
import io.petros.posts.kotlin.R
import io.petros.posts.kotlin.activity.BaseFragment
import io.petros.posts.kotlin.activity.main.viewmodel.PostsAdapter
import io.petros.posts.kotlin.activity.main.viewmodel.PostsViewModel
import io.petros.posts.kotlin.databinding.FragmentMainBinding
import io.petros.posts.kotlin.extension.toast

class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val postsViewModel: PostsViewModel by injector.instance()

    // CONTRACT // *************************************************************************************************************************

    override fun getLayoutId(): Int = R.layout.fragment_main

    override fun constructBinding(): FragmentMainBinding = DataBindingUtil.setContentView(activity, R.layout.fragment_main)

    override fun bindViewModel() {
        binding.viewModel = postsViewModel
    }

    // LIFECYCLE // ************************************************************************************************************************

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecyclerView()
    }

    private fun setRecyclerView() {
        binding.postsRecyclerView.adapter = PostsAdapter {
            toast("${it.title} Clicked")
        }
    }

    override fun onResume() {
        super.onResume()
        postsViewModel.loadPosts()
    }

}
