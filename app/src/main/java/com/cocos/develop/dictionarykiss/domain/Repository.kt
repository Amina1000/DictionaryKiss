package com.cocos.develop.dictionarykiss.domain

/**
 * homework com.cocos.develop.dictionarykiss.domain
 *
 * @author Amina
 * 26.08.2021
 */
interface Repository<T> {
    suspend fun getData(word: String): T
}