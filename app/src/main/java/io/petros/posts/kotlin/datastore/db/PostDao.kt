package io.petros.posts.kotlin.datastore.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import io.petros.posts.kotlin.model.Post

@Dao
interface PostDao {

    @Insert(onConflict = REPLACE)
    fun save(posts: List<Post>)

    @Query("SELECT * FROM post")
    fun getAll(): LiveData<List<Post>>

}
