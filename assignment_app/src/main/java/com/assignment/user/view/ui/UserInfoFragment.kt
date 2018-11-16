package com.assignment.search.view.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.assignment.GithubSearchApp
import com.assignment.R
import com.assignment.di.ApplicationComponent
import com.assignment.user.repository.UserInfoDownloadResult
import com.assignment.user.viewmodel.UserInfoViewModel
import com.assignment.user.viewmodel.UserInfoViewModelFactory
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_user_info.*
import javax.inject.Inject

/*
 * Copyright 2018, Assignment/test project for some company
 *
 * @author Stefan Wyszynski
 *
 */
class UserInfoFragment : Fragment() {
    private val GITHUB_USER_NAME = "GITHUB_USER_NAME"

    private lateinit var appComponent: ApplicationComponent

    @Inject
    lateinit var userInfoViewModelFactory: UserInfoViewModelFactory

    companion object {
        @JvmStatic
        fun newInstance(urlToGithubUser: String?): UserInfoFragment {
            return UserInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(GITHUB_USER_NAME, urlToGithubUser)
                }
            }
        }
    }

    private lateinit var viewModel: UserInfoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_info, container, false)
    }

    private lateinit var githubUserName: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        githubUserName = arguments?.getString(GITHUB_USER_NAME) ?: ""
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        injectComponent()
        activity!!.title = getString(R.string.fragment_user_info_title)
        bindViewModelData()
        downloadUserInfo()
    }

    private fun injectComponent() {
        appComponent = GithubSearchApp.getAppComponent()
        appComponent.inject(this)
    }

    private fun bindViewModelData() {
        viewModel = ViewModelProviders.of(this, userInfoViewModelFactory).get(UserInfoViewModel::class.java)
        viewModel.getGithubUserInfoResultData().observe(this, Observer { downloadResult ->
            updateUserInfo(downloadResult)
        })
    }

    private fun downloadUserInfo() {
        if (githubUserName.length > 0) {
            viewModel.downloadUserInfo(githubUserName)
        } else {
            Toast.makeText(activity, "User name is empty - cannot download", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUserInfo(result: UserInfoDownloadResult?) {
        result?.let {
            when (result.status) {
                UserInfoDownloadResult.ResultStatus.SUCCESS -> {
                    setUserUIBasedOnDownloadResult(result)
                }
                UserInfoDownloadResult.ResultStatus.FAILURE -> {
                    Toast.makeText(activity, "Error occured while downloading user info", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setUserUIBasedOnDownloadResult(result: UserInfoDownloadResult) {
        result.user?.apply {
            followersNumber.text = followers?.toString()
            userNameF.text = login
            Glide.with(activity!!.applicationContext).load(avatarUrl).into(avatarImage);
        }
    }
}
