package com.cocos.develop.dictionarykiss.data.datasource

import com.cocos.develop.dictionarykiss.data.DataModel
import com.cocos.develop.dictionarykiss.domain.DataSource
import io.reactivex.Observable

/**
 * homework com.cocos.develop.dictionarykiss.data.datasource
 *
 * @author Amina
 * 26.08.2021
 */
class DataSourceRemote(private val remoteProvider: RetrofitImplementation = RetrofitImplementation()) :
    DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}