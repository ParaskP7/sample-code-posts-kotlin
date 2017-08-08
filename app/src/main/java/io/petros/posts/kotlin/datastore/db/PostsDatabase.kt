package io.petros.posts.kotlin.datastore.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import io.petros.posts.kotlin.model.User

@Database(entities = arrayOf(User::class), version = 1, exportSchema = false)
abstract class PostsDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}
