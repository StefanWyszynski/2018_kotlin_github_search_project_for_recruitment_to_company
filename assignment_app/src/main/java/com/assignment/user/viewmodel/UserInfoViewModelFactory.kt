package com.assignment.user.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.assignment.service.GitHubApiService
import com.assignment.user.repository.UserInfoRepository
import javax.inject.Inject

class UserInfoViewModelFactory @Inject
constructor(private val userInfoRepository: UserInfoRepository, private val retrofitService: GitHubApiService) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserInfoViewModel(userInfoRepository, retrofitService) as T
    }
}