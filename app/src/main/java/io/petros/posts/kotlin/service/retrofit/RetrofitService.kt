package io.petros.posts.kotlin.service.retrofit

import io.petros.posts.kotlin.model.Comment
import io.petros.posts.kotlin.model.Post
import io.petros.posts.kotlin.model.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("/posts")
    abstract fun posts(): Observable<List<Post>>

    @GET("/users")
    abstract fun users(): Observable<List<User>>

    @GET("/comments")
    abstract fun comments(@Query("postId") postId: Int): Observable<List<Comment>>

}
