package com.cocos.develop.dictionarykiss.domain

/**
 * homework com.cocos.develop.dictionarykiss.domain
 *
 * @author Amina
 * 10.09.2021
 */
interface RepositoryLocal<T> : Repository<T> {
    suspend fun saveToDB(appState: AppState)
}