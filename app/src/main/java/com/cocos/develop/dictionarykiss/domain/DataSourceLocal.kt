package com.cocos.develop.dictionarykiss.domain

interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDB(appState: AppState)
}
