package com.assignment.model.repos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/*
 * Copyright 2018, Assignment/test project for some company
 *
 * @author Stefan Wyszynski
 */
class GithubReposSearch {
    @SerializedName("total_count")
    @Expose
    var totalCount: Int? = null

    @SerializedName("incomplete_results")
    @Expose
    var incompleteResults: Boolean? = null

    @SerializedName("items")
    @Expose
    var githubRepos: List<GithubRepo>? = null
}