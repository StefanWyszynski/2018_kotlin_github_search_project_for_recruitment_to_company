package com.assignment

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.assignment.di.ApplicationComponent
import com.assignment.di.DaggerApplicationComponent

/*
 * Copyright 2018, Assignment/test project for some company
 *
 * @author Stefan Wyszynski
 *
 */
class GithubSearchApp : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder().build()
    }

    companion object {

        private lateinit var appComponent: ApplicationComponent
        fun getAppComponent(): ApplicationComponent {
            return appComponent
        }
    }
}