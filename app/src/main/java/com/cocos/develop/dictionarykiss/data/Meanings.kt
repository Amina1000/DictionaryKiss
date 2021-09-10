package com.cocos.develop.dictionarykiss.data

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

/**
 * homework com.cocos.develop.dictionarykiss.domain
 *
 * @author Amina
 * 26.08.2021
 */
class Meanings(
    @field:SerializedName("id") val id:Int?,
    @field:SerializedName("translation") val translation: Translation?,
    @field:SerializedName("imageUrl") val imageUrl: String?
)