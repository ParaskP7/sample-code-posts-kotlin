package io.petros.posts.kotlin.datastore.cache

import io.petros.posts.kotlin.model.Post
import org.joda.time.DateTime
import org.joda.time.Duration
import timber.log.Timber

class PostsCache {

    companion object {

        private const val FRESH_CACHE_TIMEOUT_SECS = 60

    }

    var posts: List<Post>? = null
    var updatedAt: DateTime? = null


    fun get(): List<Post>? = if (isFresh()) posts else null

    private fun isFresh(): Boolean {
        if (updatedAt != null) {
            val duration = Duration(updatedAt, DateTime()).standardSeconds
            if (duration < FRESH_CACHE_TIMEOUT_SECS) {
                Timber.d("The cache is still fresh. [Cache Age: ${duration}secs]")
                return true
            }
        }
        return false
    }

    fun set(retrievedPosts: List<Post>) {
        posts = retrievedPosts
        updatedAt = DateTime()
    }

}
