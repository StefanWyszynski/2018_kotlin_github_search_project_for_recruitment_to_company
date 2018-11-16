package com.assignment.search.repository

import com.assignment.base.RepositoryBase
import com.assignment.model.repos.GithubReposSearch
import com.assignment.model.users.GithubUsersSearch
import com.assignment.service.GitHubApiService
import com.assignment.utils.Shedulers
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import javax.inject.Inject


/*
 * Copyright 2018, Assignment/test project for some company
 *
 * @author Stefan Wyszynski
 */
open class SearchRepository @Inject constructor() : RepositoryBase<SearchResult>() {

    val disposables = CompositeDisposable()

    open fun searchGithub(searchText: String, retrofitService: GitHubApiService) {
        val users = retrofitService.searchUsers(searchText)
        val repos = retrofitService.searchRepos(searchText)
        val value = object : BiFunction<GithubUsersSearch, GithubReposSearch, SearchResult> {
            override fun apply(githubUsersSearch: GithubUsersSearch, githubReposSearch: GithubReposSearch): SearchResult {
                return SearchResult.success(githubUsersSearch, githubReposSearch)
            }
        }
        val subscribe = Observable.zip(users, repos, value)
                .subscribeOn(Shedulers.ioThread())
                .observeOn(Shedulers.androidThread())
                .subscribe({ result ->
                    setValue(result)
                }, { throwable ->
                    setValue(SearchResult.failure())
                    throwable.printStackTrace()
                })
        disposables.add(subscribe)
    }

    fun disposeDownloads() {
        disposables.clear()
    }
}
