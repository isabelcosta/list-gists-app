package com.isabelcmdcosta.listgists.data.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "gist")
data class GistSimplified(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "created_at") val createdAt: String,
    @ColumnInfo(name = "updated_at") val updatedAt: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "author") val author: String
) : Parcelable