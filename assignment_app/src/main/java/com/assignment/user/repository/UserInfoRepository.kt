package com.assignment.user.repository

import com.assignment.base.RepositoryBase
import com.assignment.service.GitHubApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/*
 * Copyright 2018, Assignment/test project for some company
 *
 * @author Stefan Wyszynski
 */
class UserInfoRepository @Inject constructor() : RepositoryBase<UserInfoDownloadResult>() {

    private var disposable: Disposable? = null

    fun getUserFromGithub(userName: String, retrofitService: GitHubApiService) {
        disposable = retrofitService.getUser(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    setValue(UserInfoDownloadResult.success(result))
                }, { throwable ->
                    setValue(UserInfoDownloadResult.failure())
                    throwable.printStackTrace()
                })
    }

    fun disposeDownload() {
        if (!(disposable?.isDisposed ?: false)) {
            disposable!!.dispose()
        }
    }
}