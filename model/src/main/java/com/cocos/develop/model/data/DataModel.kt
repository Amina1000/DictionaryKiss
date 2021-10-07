package com.cocos.develop.model.data

import com.google.gson.annotations.SerializedName

/**
 * homework com.cocos.develop.dictionarykiss.domain
 *
 * @author Amina
 * 26.08.2021
 */
class DataModel(
    @field:SerializedName("id") val id:Int?,
    @field:SerializedName("text") val text: String?,
    @field:SerializedName("meanings") val meanings: List<Meanings>?,
    @field:SerializedName("favorite") var favorite: Boolean = false
)
