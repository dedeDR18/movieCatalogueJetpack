package id.learn.android.proyekacademy.data.source.local.entity

import androidx.room.ColumnInfo

data class ContentEntity(
    @ColumnInfo(name = "content")
    var content: String?
)