package com.cocos.develop.repository.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(indices = arrayOf(Index(value = arrayOf("word"), unique = true)))
class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int = 0,
    @field:ColumnInfo(name = "word")
    var word: String,
    @field:ColumnInfo(name = "description")
    var translation: String?,
    @field:ColumnInfo(name = "imageUrl")
    var imageUrl: String?,
    @field:ColumnInfo(name = "note")
    var note: String?,
    @field:ColumnInfo(name = "previewUrl")
    var previewUrl: String?,
    @field:ColumnInfo(name = "transcription")
    var transcription: String?,
    @field:ColumnInfo(name = "soundUrl")
    var soundUrl: String?,
    @field:ColumnInfo(name = "favorite")
    var favorite: Boolean = false
)
