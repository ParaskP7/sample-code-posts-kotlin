package io.petros.posts.kotlin.app

import android.arch.persistence.room.Room
import android.content.Context
import io.petros.posts.kotlin.R
import io.petros.posts.kotlin.activity.main.viewmodel.PostsAdapter
import io.petros.posts.kotlin.datastore.cache.PostsCache
import io.petros.posts.kotlin.datastore.db.PostsDatabase
import io.petros.posts.kotlin.datastore.db.UserDao
import io.petros.posts.kotlin.extension.toast
import io.petros.posts.kotlin.service.retrofit.WebService
import io.petros.posts.kotlin.util.rx.RxSchedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun constructPostsAdapter(context: Context): PostsAdapter = PostsAdapter { context.toast("${it.id} ${it.title}") }

fun constructRxSchedulers(): RxSchedulers {
    return RxSchedulers(Schedulers.io(), Schedulers.computation(), Schedulers.trampoline(), AndroidSchedulers.mainThread())
}

fun constructWebService(context: Context): WebService {
    val retrofit = Retrofit.Builder()
            .baseUrl(context.getString(R.string.posts_url))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    return retrofit.create(WebService::class.java)
}

fun constructPostsCache(): PostsCache = PostsCache()

fun constructPostsDatabase(context: Context): PostsDatabase = Room.databaseBuilder(context, PostsDatabase::class.java, "posts.db")
        .build()

fun constructUserDao(postsDatabase: PostsDatabase): UserDao = postsDatabase.userDao()
