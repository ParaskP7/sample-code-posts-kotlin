package io.petros.posts.kotlin.datastore.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import io.petros.posts.kotlin.model.User
import io.reactivex.Single

@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    fun save(users: List<User>)

    @Query("SELECT * FROM user WHERE id = :id")
    fun get(id: String): Single<User>

}
