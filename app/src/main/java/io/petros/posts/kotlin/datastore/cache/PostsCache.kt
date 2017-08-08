package io.petros.posts.kotlin.datastore.cache

import io.petros.posts.kotlin.model.Post

class PostsCache {

    var posts: List<Post>? = null


    fun get(): List<Post>? {
        return posts
    }

    fun set(retrievedPosts: List<Post>) {
        posts = retrievedPosts
    }

}
