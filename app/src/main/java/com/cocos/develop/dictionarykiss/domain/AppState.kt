package com.cocos.develop.dictionarykiss.domain

import com.cocos.develop.dictionarykiss.data.DataModel
import io.reactivex.Observable

/**
 * homework com.cocos.develop.dictionarykiss.domain
 *
 * @author Amina
 * 26.08.2021
 */
sealed class AppState {
    data class Success(val data:List<DataModel>?) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}