package com.assignment.search.repository

import com.assignment.model.repos.GithubReposSearch
import com.assignment.model.users.GithubUsersSearch
import com.assignment.search.view.adapter.IGithubSearchResultMarker

/*
 * Copyright 2018, Assignment/test project for some company
 *
 * @author Stefan Wyszynski
 */
class SearchResult(val users: GithubUsersSearch?, val repos: GithubReposSearch?, var status: ResultStatus) {

    enum class ResultStatus {
        SUCCESS, FAILURE
    }

    fun getResultAsSingleList(): List<IGithubSearchResultMarker> {
        val users = users?.githubUsers?.sortedBy { it.id } ?: arrayListOf<IGithubSearchResultMarker>()
        val repos = repos?.githubRepos?.sortedBy { it.id } ?: arrayListOf<IGithubSearchResultMarker>()
        return arrayListOf(users, repos).flatten()
    }

    companion object {

        fun success(users: GithubUsersSearch?, repos: GithubReposSearch?): SearchResult {
            return SearchResult(users, repos, ResultStatus.SUCCESS)
        }

        fun failure(): SearchResult {
            return SearchResult(null, null, ResultStatus.FAILURE)
        }
    }
}