package com.cocos.develop.model.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * homework com.cocos.develop.dictionarykiss.domain
 *
 * @author Amina
 * 26.08.2021
 */
@Parcelize
class Meanings(
    @field:SerializedName("id") val id:Int?,
    @field:SerializedName("translation") val translation: Translation?,
    @field:SerializedName("imageUrl") val imageUrl: String?,
    @field:SerializedName("previewUrl") val previewUrl: String?,
    @field:SerializedName("transcription") val transcription: String?,
    @field:SerializedName("soundUrl") val soundUrl: String?,
):Parcelable