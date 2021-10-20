package com.cocos.develop.repository.domain

import com.cocos.develop.model.data.DataModel

interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDB(dataModel: DataModel)
    suspend fun getFavorite():T
}
