package com.assignment.service

import com.assignment.model.repos.GithubReposSearch
import com.assignment.model.users.GithubUser
import com.assignment.model.users.GithubUsersSearch
import io.reactivex.Observable

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

/*
 * Copyright 2018, Assignment/test project for some company
 *
 * @author Stefan Wyszynski
 */
interface GitHubApiService {
    @Headers("Accept: application/vnd.github.mercy-preview+json")
    @GET("search/repositories")
    fun searchRepos(@Query("q") term: String): Observable<GithubReposSearch>

    @Headers("Accept: application/vnd.github.mercy-preview+json")
    @GET("search/users")
    fun searchUsers(@Query("q") term: String): Observable<GithubUsersSearch>

    @Headers("Accept: application/vnd.github.mercy-preview+json")
    @GET("https://api.github.com/users/{user}")
    fun getUser(@Path("user") userName: String): Observable<GithubUser>
}