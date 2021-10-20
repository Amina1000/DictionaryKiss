package com.cocos.develop.repository.domain

import com.cocos.develop.model.data.AppState

/**
 * homework com.cocos.develop.dictionarykiss.domain
 *
 * @author Amina
 * 10.09.2021
 */
interface RepositoryLocal<T> : Repository<T> {
    suspend fun saveToDB(appState: AppState)
}