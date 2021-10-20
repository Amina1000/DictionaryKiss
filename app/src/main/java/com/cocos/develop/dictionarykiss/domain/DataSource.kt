package com.cocos.develop.dictionarykiss.domain

import io.reactivex.Observable

/**
 * homework com.cocos.develop.dictionarykiss.domain
 *
 * @author Amina
 * 26.08.2021
 */
interface DataSource<T> {
    suspend fun getData(word: String): T
}