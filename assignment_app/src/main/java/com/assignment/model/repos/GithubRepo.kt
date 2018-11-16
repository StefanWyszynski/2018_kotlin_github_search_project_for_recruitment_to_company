package com.assignment.model.repos

import com.assignment.model.users.GithubUser
import com.assignment.search.view.adapter.IGithubSearchResultMarker
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/*
 * Copyright 2018, Assignment/test project for some company
 *
 * @author Stefan Wyszynski
 */
class GithubRepo : IGithubSearchResultMarker {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("full_name")
    @Expose
    var fullName: String? = null
    @SerializedName("private")
    @Expose
    var _private: Boolean? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null
    @SerializedName("size")
    @Expose
    var size: Int? = null
    @SerializedName("stargazers_count")
    @Expose
    var stargazersCount: Int? = null
    @SerializedName("language")
    @Expose
    var language: String? = null
    @SerializedName("has_wiki")
    @Expose
    var hasWiki: Boolean? = null
    @SerializedName("archived")
    @Expose
    var archived: Boolean? = null
    @SerializedName("score")
    @Expose
    var score: Double? = null

    @SerializedName("owner")
    @Expose
    var owner: GithubUser? = null

    override fun getUserName(): String? {
        return owner?.login ?: fullName
    }

    override fun getRepoName(): String? {
        return name
    }
}
