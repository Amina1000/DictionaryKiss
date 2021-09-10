package com.cocos.develop.dictionarykiss.data.repository

import com.cocos.develop.dictionarykiss.data.DataModel
import com.cocos.develop.dictionarykiss.domain.AppState
import com.cocos.develop.dictionarykiss.domain.DataSourceLocal
import com.cocos.develop.dictionarykiss.domain.RepositoryLocal


class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<DataModel>>) :
    RepositoryLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }
}
