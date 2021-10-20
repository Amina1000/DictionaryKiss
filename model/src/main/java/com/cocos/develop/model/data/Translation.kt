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
class Translation (
    @field:SerializedName("text") val translation: String?,
    @field:SerializedName("note") val note: String?
    ):Parcelable