package com.assignment.search.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.assignment.search.repository.SearchRepository
import com.assignment.service.GitHubApiService
import javax.inject.Inject

class SearchViewModelFactory @Inject
constructor(private val searchRepository: SearchRepository, private val retrofitService: GitHubApiService) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(searchRepository, retrofitService) as T
    }
}