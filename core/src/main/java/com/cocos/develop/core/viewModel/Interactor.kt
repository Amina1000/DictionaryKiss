package com.cocos.develop.core.viewModel

import com.cocos.develop.model.data.DataModel


/**
 * homework com.cocos.develop.dictionarykiss.domain.presenter
 *
 * @author Amina
 * 26.08.2021
 */
interface Interactor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}