package com.cocos.develop.repository.datasource

import com.cocos.develop.model.data.DataModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * homework com.cocos.develop.dictionarykiss.data.datasource
 *
 * @author Amina
 * 26.08.2021
 */
interface ApiService {
    @GET("words/search")
    fun search(@Query("search") wordToSearch: String): Deferred<List<DataModel>>
}