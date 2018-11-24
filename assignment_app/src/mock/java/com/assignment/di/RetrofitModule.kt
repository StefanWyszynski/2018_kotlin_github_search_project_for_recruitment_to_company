package com.assignment.di

import com.assignment.service.GitHubApiService
import dagger.Module
import dagger.Provides
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
    fun provideGitHubApiService(): GitHubApiService {
        return GitHubApiServiceMock()
    }

}
