package com.assignment.user.repository

import com.assignment.model.users.GithubUser

/*
 * Copyright 2018, Assignment/test project for some company
 *
 * @author Stefan Wyszynski
 */
class UserInfoDownloadResult(var user: GithubUser?, var status: ResultStatus) {

    enum class ResultStatus {
        SUCCESS, FAILURE
    }

    companion object {

        fun success(user: GithubUser): UserInfoDownloadResult {
            return UserInfoDownloadResult(user, ResultStatus.SUCCESS)
        }

        fun failure(): UserInfoDownloadResult {
            return UserInfoDownloadResult(null, ResultStatus.FAILURE)
        }
    }
}