package com.assignment.user.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.assignment.service.GitHubApiService
import com.assignment.user.repository.UserInfoDownloadResult
import com.assignment.user.repository.UserInfoRepository
import javax.inject.Inject

/*
 * Copyright 2018, Assignment/test project for some company
 *
 * @author Stefan Wyszynski
 */
class UserInfoViewModel : ViewModel {

    var userInfoRepository: UserInfoRepository
    var retrofitService: GitHubApiService

    @Inject
    constructor(userInfoRepository: UserInfoRepository, retrofitService: GitHubApiService) : super() {
        this.userInfoRepository = userInfoRepository
        this.retrofitService = retrofitService
    }

    override fun onCleared() {
        userInfoRepository.disposeDownload()
        super.onCleared()
    }

    fun getGithubUserInfoResultData(): LiveData<UserInfoDownloadResult> = userInfoRepository.liveData

    fun downloadUserInfo(userName: String) {
        userInfoRepository.getUserFromGithub(userName, retrofitService)
    }
}