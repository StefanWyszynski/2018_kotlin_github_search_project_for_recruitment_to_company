package com.assignment.search.viewmodel

import com.assignment.di.GitHubApiServiceMock
import com.assignment.search.repository.SearchRepository
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class SearchViewModelTest {

    lateinit var searchViewModel: SearchViewModel

    @Mock
    lateinit var searchRepository: SearchRepository

    @Mock
    lateinit var githubService: GitHubApiServiceMock

    val test_repouser_str = "test_repouser_str"

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        MockitoAnnotations.initMocks(this)
        searchViewModel = SearchViewModel(searchRepository, githubService)
    }

    @Test
    fun searchForUserOrRepositoryReturnsUsersAndRepositories() {
        searchViewModel.searchForUserOrRepository(test_repouser_str)
        verify(searchRepository).searchGithub(test_repouser_str, githubService)
    }
}