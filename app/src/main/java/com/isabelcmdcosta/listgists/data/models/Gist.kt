package com.isabelcmdcosta.listgists.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Gist(
        val id: String,
        @SerializedName("created_at") val createdAt: String,
        @SerializedName("updated_at") val updatedAt: String,
        val description: String,
        val owner: Owner,
        val url: String,
        val comments: Int,
        val comments_url: String,
        val commits_url: String,
//        val files: List<File>,
        val forks_url: String,
        val git_pull_url: String,
        val git_push_url: String,
        val html_url: String,
        val node_id: String,
        val public: Boolean,
        val truncated: Boolean
//        val user: Any
) : Parcelable

@Parcelize
data class File(
    val filename: String,
    val language: String,
    val raw_url: String,
    val size: Int,
    val type: String
) : Parcelable

@Parcelize
data class Owner(
    val id: Int,
    val url: String,
    @SerializedName("login") val username: String,
    val avatar_url: String,
    val events_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val gravatar_id: String,
    val html_url: String,
    val node_id: String,
    val organizations_url: String,
    val received_events_url: String,
    val repos_url: String,
    val site_admin: Boolean,
    val starred_url: String,
    val subscriptions_url: String,
    val type: String
) : Parcelable
