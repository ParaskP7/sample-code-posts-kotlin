package io.petros.posts.kotlin.model

import android.arch.persistence.room.*

@Entity(tableName = "comment",
        foreignKeys = arrayOf(
                ForeignKey(entity = Post::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("post_id"),
                        onDelete = ForeignKey.CASCADE)),
        indices = arrayOf(Index(value = "post_id")))
class Comment(@PrimaryKey val id: String,
              @ColumnInfo(name = "post_id") val postId: String,
              val name: String,
              val email: String,
              val body: String)
