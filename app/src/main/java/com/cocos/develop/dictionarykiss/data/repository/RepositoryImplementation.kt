package com.cocos.develop.dictionarykiss.data.repository

import com.cocos.develop.dictionarykiss.data.DataModel
import com.cocos.develop.dictionarykiss.domain.DataSource
import com.cocos.develop.dictionarykiss.domain.Repository
import io.reactivex.Observable

/**
 * homework com.cocos.develop.dictionarykiss.data.repository
 *
 * @author Amina
 * 26.08.2021
 */
class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}