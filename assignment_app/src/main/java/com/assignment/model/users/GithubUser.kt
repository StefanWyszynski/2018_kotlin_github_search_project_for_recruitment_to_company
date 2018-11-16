package com.assignment.model.users

import com.assignment.search.view.adapter.IGithubSearchResultMarker
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/*
 * Copyright 2018, Assignment/test project for some company
 *
 * @author Stefan Wyszynski
 */
open class GithubUser : IGithubSearchResultMarker {

    @SerializedName("login")
    @Expose
    var login: String? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("node_id")
    @Expose
    var nodeId: String? = null
    @SerializedName("avatar_url")
    @Expose
    var avatarUrl: String? = null
    @SerializedName("gravatar_id")
    @Expose
    var gravatarId: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("html_url")
    @Expose
    var htmlUrl: String? = null
    @SerializedName("followers_url")
    @Expose
    var followersUrl: String? = null
    @SerializedName("following_url")
    @Expose
    var followingUrl: String? = null

    @SerializedName("gists_url")
    @Expose
    var gistsUrl: String? = null

    @SerializedName("starred_url")
    @Expose
    var starredUrl: String? = null

    @SerializedName("subscriptions_url")
    @Expose
    var subscriptionsUrl: String? = null

    @SerializedName("organizations_url")
    @Expose
    var organizationsUrl: String? = null

    @SerializedName("repos_url")
    @Expose
    var reposUrl: String? = null

    @SerializedName("events_url")
    @Expose
    var eventsUrl: String? = null

    @SerializedName("received_events_url")
    @Expose
    var receivedEventsUrl: String? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("site_admin")
    @Expose
    var siteAdmin: Boolean? = null

    @SerializedName("score")
    @Expose
    var score: Double? = null

    @SerializedName("followers")
    @Expose
    var followers: Int? = null

    override fun getUserName(): String? {
        return login
    }

    override fun getRepoName(): String? {
        return ""
    }
}