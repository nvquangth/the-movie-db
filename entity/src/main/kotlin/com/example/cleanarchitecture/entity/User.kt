package com.example.cleanarchitecture.entity

data class User(
    val id: Int,
    val avatarUrl: String? = null,
    val eventsUrl: String? = null,
    val followersUrl: String? = null,
    val followingUrl: String? = null,
    val gistsUrl: String? = null,
    val gravatarId: String? = null,
    val htmlUrl: String? = null,
    val login: String? = null,
    val nodeId: String? = null,
    val organizationsUrl: String? = null,
    val receivedEventsUrl: String? = null,
    val reposUrl: String? = null,
    val score: Int? = null,
    val siteAdmin: Boolean? = null,
    val starredUrl: String? = null,
    val subscriptionsUrl: String? = null,
    val type: String? = null,
    val url: String? = null
) : Model()
