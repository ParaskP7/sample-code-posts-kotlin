package io.petros.posts.kotlin.datastore.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import io.petros.posts.kotlin.model.Comment
import io.petros.posts.kotlin.model.Post
import io.petros.posts.kotlin.model.User

@Database(entities = arrayOf(User::class, Post::class, Comment::class), version = 1, exportSchema = false)
abstract class PostsDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun postDao(): PostDao

    abstract fun commentDao(): CommentDao

}
