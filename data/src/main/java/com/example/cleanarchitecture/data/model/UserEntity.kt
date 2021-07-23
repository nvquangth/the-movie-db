package com.example.cleanarchitecture.data.model

import com.example.cleanarchitecture.data.base.EntityMapper
import com.example.cleanarchitecture.data.base.ModelEntity
import com.example.cleanarchitecture.entity.User
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class UserEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    @SerializedName("events_url")
    val eventsUrl: String? = null,
    @SerializedName("followers_url")
    val followersUrl: String? = null,
    @SerializedName("following_url")
    val followingUrl: String? = null,
    @SerializedName("gists_url")
    val gistsUrl: String? = null,
    @SerializedName("gravatar_id")
    val gravatarId: String? = null,
    @SerializedName("html_url")
    val htmlUrl: String? = null,
    @SerializedName("login")
    val login: String? = null,
    @SerializedName("node_id")
    val nodeId: String? = null,
    @SerializedName("organizations_url")
    val organizationsUrl: String? = null,
    @SerializedName("received_events_url")
    val receivedEventsUrl: String? = null,
    @SerializedName("repos_url")
    val reposUrl: String? = null,
    @SerializedName("score")
    val score: Int? = null,
    @SerializedName("site_admin")
    val siteAdmin: Boolean? = null,
    @SerializedName("starred_url")
    val starredUrl: String? = null,
    @SerializedName("subscriptions_url")
    val subscriptionsUrl: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("url")
    val url: String? = null
) : ModelEntity()

class UserEntityMapper @Inject constructor() : EntityMapper<User, UserEntity> {
    override fun mapToData(model: User): UserEntity = UserEntity(
        id = model.id,
        avatarUrl = model.avatarUrl,
        eventsUrl = model.eventsUrl,
        followersUrl = model.followersUrl,
        followingUrl = model.followingUrl,
        gistsUrl = model.gistsUrl,
        gravatarId = model.gravatarId,
        htmlUrl = model.htmlUrl,
        login = model.login,
        nodeId = model.nodeId,
        organizationsUrl = model.organizationsUrl,
        receivedEventsUrl = model.receivedEventsUrl,
        reposUrl = model.reposUrl,
        score = model.score,
        siteAdmin = model.siteAdmin,
        starredUrl = model.starredUrl,
        subscriptionsUrl = model.subscriptionsUrl,
        type = model.type,
        url = model.url
    )

    override fun mapToDomain(entity: UserEntity): User = User(
        id = entity.id,
        avatarUrl = entity.avatarUrl,
        eventsUrl = entity.eventsUrl,
        followersUrl = entity.followersUrl,
        followingUrl = entity.followingUrl,
        gistsUrl = entity.gistsUrl,
        gravatarId = entity.gravatarId,
        htmlUrl = entity.htmlUrl,
        login = entity.login,
        nodeId = entity.nodeId,
        organizationsUrl = entity.organizationsUrl,
        receivedEventsUrl = entity.receivedEventsUrl,
        reposUrl = entity.reposUrl,
        score = entity.score,
        siteAdmin = entity.siteAdmin,
        starredUrl = entity.starredUrl,
        subscriptionsUrl = entity.subscriptionsUrl,
        type = entity.type,
        url = entity.url
    )
}
