package com.cocos.develop.model.data

import com.google.gson.annotations.SerializedName

/**
 * homework com.cocos.develop.dictionarykiss.domain
 *
 * @author Amina
 * 26.08.2021
 */
class Translation (
    @field:SerializedName("text") val translation: String?,
    @field:SerializedName("note") val note: String?
    )