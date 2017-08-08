package io.petros.posts.kotlin.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity data class User(@PrimaryKey val id: String, val name: String, val username: String, val email: String)
