package com.cocos.develop.repository

import com.cocos.develop.model.data.DataModel
import com.cocos.develop.repository.domain.DataSource
import com.cocos.develop.repository.domain.Repository

/**
 * homework com.cocos.develop.dictionarykiss.data.repository
 *
 * @author Amina
 * 26.08.2021
 */
class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

}