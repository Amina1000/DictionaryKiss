package com.cocos.develop.repository.datasource

import com.cocos.develop.model.data.DataModel
import com.cocos.develop.repository.room.HistoryDao
import com.cocos.develop.model.data.AppState
import com.cocos.develop.repository.domain.DataSourceLocal
import com.cocos.develop.repository.utils.convertDataModelSuccessToEntity
import com.cocos.develop.repository.utils.mapHistoryEntityToSearchResult

/**
 * homework com.cocos.develop.dictionarykiss.data.datasource
 *
 * @author Amina
 * 26.08.2021
 */
class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(dataModel: DataModel) {
        convertDataModelSuccessToEntity(dataModel)?.let {
            historyDao.insert(it)
        }
    }

    override suspend fun getFavorite(): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.getFavorite())
    }
}