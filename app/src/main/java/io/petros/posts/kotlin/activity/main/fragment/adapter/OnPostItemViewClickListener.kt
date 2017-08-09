package io.petros.posts.kotlin.activity.main.fragment.adapter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import io.petros.posts.kotlin.activity.post.PostActivity
import io.petros.posts.kotlin.model.Post

class OnPostItemViewClickListener(val activity: Activity) : OnViewClickListener {

    override fun onViewClick(post: Post) {
        val intent = Intent(activity, PostActivity::class.java)
        intent.putExtras(getExtras(post))
        activity.startActivity(intent)
    }

    fun getExtras(post: Post): Bundle {
        val extras = Bundle()
        extras.putString("id", post.id)
        return extras
    }

}
