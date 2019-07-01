package com.toshihiro.myapplication.service

import com.toshihiro.myapplication.model.User
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubAPI {

    @GET("/users/{username}")
    fun getUser(@Path("username") username: String): Observable<Response<User>>
}