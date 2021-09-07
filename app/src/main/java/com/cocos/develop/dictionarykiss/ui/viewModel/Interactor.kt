package com.cocos.develop.dictionarykiss.ui.viewModel


/**
 * homework com.cocos.develop.dictionarykiss.domain.presenter
 *
 * @author Amina
 * 26.08.2021
 */
interface Interactor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}