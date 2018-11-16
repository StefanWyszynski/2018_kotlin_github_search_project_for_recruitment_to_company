package com.assignment.di

import com.assignment.search.view.ui.SearchFragment
import com.assignment.search.view.ui.UserInfoFragment
import dagger.Component
import javax.inject.Singleton

/*
 * Copyright 2018, Assignment/test project for some company
 *
 * @author Stefan Wyszynski
 */
@Component(modules = arrayOf(RetrofitModule::class))
@Singleton
interface ApplicationComponent {
    fun inject(searchFragment: SearchFragment)
    fun inject(userInfoFragment: UserInfoFragment)
}
