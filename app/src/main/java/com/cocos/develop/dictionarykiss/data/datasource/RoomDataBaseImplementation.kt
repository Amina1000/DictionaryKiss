package com.cocos.develop.dictionarykiss.data.datasource

import com.cocos.develop.dictionarykiss.data.DataModel
import com.cocos.develop.dictionarykiss.data.room.HistoryDao
import com.cocos.develop.dictionarykiss.domain.AppState
import com.cocos.develop.dictionarykiss.domain.DataSourceLocal
import com.cocos.develop.dictionarykiss.utils.convertDataModelSuccessToEntity
import com.cocos.develop.dictionarykiss.utils.mapHistoryEntityToSearchResult

/**
 * homework com.cocos.develop.dictionarykiss.data.datasource
 *
 * @author Amina
 * 26.08.2021
 */
class RoomDataBaseImplementation(private val historyDao: HistoryDao) : DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}