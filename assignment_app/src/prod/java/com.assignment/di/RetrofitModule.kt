package com.assignment.di

import com.assignment.service.GitHubApiService
import com.assignment.utils.Shedulers
import com.google.gson.Gson
import com.google.gson.GsonBuilder

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/*
 * Copyright 2018, Assignment/test project for some company
 *
 * @author Stefan Wyszynski
 */
@Module
class RetrofitModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(HTTP_GITHUB_ROOT_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Shedulers.ioThread()))
                .build()
    }

    @Provides
    @Singleton
    fun provideGitHubApiService(retrofit: Retrofit): GitHubApiService {
        return retrofit.create(GitHubApiService::class.java)
    }

    companion object {
        val HTTP_GITHUB_ROOT_ENDPOINT = "https://api.github.com"
    }

}
