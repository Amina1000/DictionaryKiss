package com.cocos.develop.repository

import com.cocos.develop.model.data.DataModel
import com.cocos.develop.model.data.AppState
import com.cocos.develop.repository.domain.DataSourceLocal
import com.cocos.develop.repository.domain.RepositoryLocal


class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<DataModel>>) :
    RepositoryLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(dataModel: DataModel) {
        dataSource.saveToDB(dataModel)
    }

    override suspend fun getFavorite(): List<DataModel> {
       return dataSource.getFavorite()
    }

}
