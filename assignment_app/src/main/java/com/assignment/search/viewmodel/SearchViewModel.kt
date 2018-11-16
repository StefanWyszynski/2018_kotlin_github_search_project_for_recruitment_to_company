package com.assignment.search.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.assignment.search.repository.SearchRepository
import com.assignment.search.repository.SearchResult
import com.assignment.service.GitHubApiService
import javax.inject.Inject


/*
 * Copyright 2018, Assignment/test project for some company
 *
 * @author Stefan Wyszynski
 */
class SearchViewModel : ViewModel {

    var searchRepository: SearchRepository
    var retrofitService: GitHubApiService

    @Inject
    constructor(searchRepository: SearchRepository, retrofitService: GitHubApiService) : super() {
        this.searchRepository = searchRepository
        this.retrofitService = retrofitService
    }

    override fun onCleared() {
        searchRepository.disposeDownloads()
        super.onCleared()
    }

    fun getGithubSearchResultData(): LiveData<SearchResult> = searchRepository.liveData

    fun searchForUserOrRepository(userOrRepositoryName: String) {
        searchRepository.searchGithub(userOrRepositoryName, retrofitService);
    }
}

