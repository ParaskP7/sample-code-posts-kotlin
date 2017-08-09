package io.petros.posts.kotlin.model

import android.arch.persistence.room.*

@Entity(tableName = "post",
        foreignKeys = arrayOf(
                ForeignKey(entity = User::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("user_id"),
                        onDelete = ForeignKey.CASCADE)),
        indices = arrayOf(Index(value = "user_id")))
data class Post(@PrimaryKey val id: String,
                @ColumnInfo(name = "user_id") val userId: String,
                val title: String,
                val body: String)
