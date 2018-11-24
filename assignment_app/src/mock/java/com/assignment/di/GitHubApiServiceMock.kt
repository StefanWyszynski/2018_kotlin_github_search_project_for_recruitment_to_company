package com.assignment.di

import com.assignment.model.repos.GithubReposSearch
import com.assignment.model.users.GithubUser
import com.assignment.model.users.GithubUsersSearch
import com.assignment.service.GitHubApiService
import com.assignment.testutils.TestUtils
import io.reactivex.Observable

/*
 * Copyright 2018, Assignment/test project for some company
 *
 * @author Stefan Wyszynski
 */
open class GitHubApiServiceMock : GitHubApiService {
    override fun searchRepos(term: String): Observable<GithubReposSearch> {
        var gitSearch = TestUtils.loadJson("mock/get_search_repos.json",GithubReposSearch::class.java)
        return Observable.just(gitSearch)
    }

    override fun searchUsers(term: String): Observable<GithubUsersSearch> {
        var gitSearch = TestUtils.loadJson("mock/get_search_users.json",GithubUsersSearch::class.java)
        return Observable.just(gitSearch)
    }

    override fun getUser(userName: String): Observable<GithubUser> {
        var gitSearch = TestUtils.loadJson("mock/get_user.json",GithubUser::class.java)
        return Observable.just(gitSearch)
    }

}