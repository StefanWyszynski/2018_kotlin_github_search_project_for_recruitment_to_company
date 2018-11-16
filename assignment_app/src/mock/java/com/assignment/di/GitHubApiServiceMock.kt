package com.assignment.di

import com.assignment.model.repos.GithubRepo
import com.assignment.model.repos.GithubReposSearch
import com.assignment.model.users.GithubUser
import com.assignment.model.users.GithubUsersSearch
import com.assignment.service.GitHubApiService
import io.reactivex.Observable

open class GitHubApiServiceMock : GitHubApiService {
    override fun searchRepos(term: String): Observable<GithubReposSearch> {
        val githubReposSearch = GithubReposSearch()
        val repo = GithubRepo()
        repo.id = 1
        repo.fullName = "testuser/test"
        githubReposSearch.githubRepos = arrayListOf(repo)
        return Observable.just(githubReposSearch)
    }

    override fun searchUsers(term: String): Observable<GithubUsersSearch> {
        val githubUsersSearch = GithubUsersSearch()
        val githubUser = GithubUser()
        githubUser.login = "testUser"
        githubUser.avatarUrl = ""
        githubUser.id = 3;
        githubUser.followers = 55
        githubUsersSearch.githubUsers = arrayListOf(githubUser)
        return Observable.just(githubUsersSearch)
    }

    override fun getUser(userName: String): Observable<GithubUser> {
        val githubUser = GithubUser()
        githubUser.login = "testUser"
        githubUser.avatarUrl = ""
        githubUser.id = 3
        githubUser.followers = 55
        return Observable.just(githubUser)
    }

}