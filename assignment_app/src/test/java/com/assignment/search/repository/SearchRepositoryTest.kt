package com.assignment.search.repository

import com.assignment.di.GitHubApiServiceMock
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.spy
import org.mockito.runners.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class SearchRepositoryTest {

    lateinit var searchRepository: SearchRepository
    lateinit var githubService: GitHubApiServiceMock
    val test_repouser_str = "test_repouser_str"

//    @get:Rule @JvmField
//    var rule: TestRule = InstantTaskExecutorRule()

//    companion object {
//        @get:Rule
//        @JvmField
//        val schedulers = RxImmediateSchedulerRule()
//    }

    @Before
    fun setUp() {
        githubService = spy(GitHubApiServiceMock())
        searchRepository = spy(SearchRepository())
    }

    private fun <T> any(type: Class<T>): T = Mockito.any<T>(type)

    @Test
    fun searchGithub() {
        searchRepository.searchGithub(test_repouser_str, githubService)

        Mockito.verify(githubService).searchUsers(test_repouser_str)
        Mockito.verify(githubService).searchRepos(test_repouser_str)
        Mockito.verify(searchRepository).setValue(any(SearchResult::class.java))
    }
}