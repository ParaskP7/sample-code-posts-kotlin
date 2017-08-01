package io.petros.posts.kotlin.activity.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import io.petros.posts.kotlin.R
import io.petros.posts.kotlin.activity.BaseFragment
import io.petros.posts.kotlin.databinding.FragmentMainBinding
import io.petros.posts.kotlin.extension.toast
import io.petros.posts.kotlin.model.Post
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment() {

    // LIFECYCLE // ************************************************************************************************************************

    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindPost()
        setRecyclerView()
    }

    private fun bindPost() {
        val binding: FragmentMainBinding = DataBindingUtil.setContentView(activity, R.layout.fragment_main)
        binding.post = Post(0, 0, "Title 0", "Body 0")
    }

    private fun setRecyclerView() {
        val posts = listOf(Post(1, 1, "Title 1", "Body 1"), Post(2, 2, "Title 2", "Body 2"))
        postsRecyclerView.adapter = PostsAdapter(posts) {
            toast("${it.title} Clicked")
        }
    }

}
