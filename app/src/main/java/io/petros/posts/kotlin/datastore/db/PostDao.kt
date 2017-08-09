package io.petros.posts.kotlin.datastore.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import io.petros.posts.kotlin.model.User

@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    fun save(users: List<User>)

}
