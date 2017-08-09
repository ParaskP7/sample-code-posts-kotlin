package io.petros.posts.kotlin.activity.main.viewmodel

import android.support.v7.widget.RecyclerView
import android.view.View
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance
import io.petros.posts.kotlin.R
import io.petros.posts.kotlin.datastore.Datastore
import io.petros.posts.kotlin.extension.loadUrl
import io.petros.posts.kotlin.model.Post
import io.petros.posts.kotlin.util.rx.RxSchedulers
import kotlinx.android.synthetic.main.item_view_post.view.*

class PostViewHolder(override val kodein: Kodein, itemView: View) : KodeinAware, RecyclerView.ViewHolder(itemView) {

    private val rxSchedulers: RxSchedulers = instance()
    private val datastore: Datastore = instance()

    fun bind(post: Post, listener: (Post) -> Unit) = with(itemView) {
        setCircleImageView(post)
        setTitleTextView(post)
        setOnClickListener { listener(post) }
    }

    private fun setCircleImageView(post: Post) = with(itemView) {
        datastore.getUser(post.userId)
                .observeOn(rxSchedulers.androidMainThreadScheduler)
                .subscribeOn(rxSchedulers.ioScheduler)
                .subscribe { user -> postCircleImageView.loadUrl(context.getString(R.string.avatar_url, user.email)) }
    }

    fun setTitleTextView(post: Post) = with(itemView) {
        postTitleTextView.text = post.title
    }

}
