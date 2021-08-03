package com.example.cleanarchitecture.model

import android.os.Parcelable
import com.bt.presentation.base.model.ItemMapper
import com.bt.presentation.base.model.ModelItem
import com.example.cleanarchitecture.entity.User
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@Parcelize
data class UserItem(
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
) : ModelItem(), Parcelable

class UserItemMapper @Inject constructor() : ItemMapper<User, UserItem> {
    override fun mapToPresentation(model: User): UserItem = UserItem(
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

    override fun mapToDomain(item: UserItem): User = User(
        id = item.id,
        avatarUrl = item.avatarUrl,
        eventsUrl = item.eventsUrl,
        followersUrl = item.followersUrl,
        followingUrl = item.followingUrl,
        gistsUrl = item.gistsUrl,
        gravatarId = item.gravatarId,
        htmlUrl = item.htmlUrl,
        login = item.login,
        nodeId = item.nodeId,
        organizationsUrl = item.organizationsUrl,
        receivedEventsUrl = item.receivedEventsUrl,
        reposUrl = item.reposUrl,
        score = item.score,
        siteAdmin = item.siteAdmin,
        starredUrl = item.starredUrl,
        subscriptionsUrl = item.subscriptionsUrl,
        type = item.type,
        url = item.url
    )
}
