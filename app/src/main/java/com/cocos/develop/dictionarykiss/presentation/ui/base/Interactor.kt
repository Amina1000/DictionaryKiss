package com.cocos.develop.dictionarykiss.presentation.ui.base

import io.reactivex.Observable

/**
 * homework com.cocos.develop.dictionarykiss.domain.presenter
 *
 * @author Amina
 * 26.08.2021
 */
interface Interactor<T> {
    fun getData(word: String, fromRemoteSource: Boolean): Observable<T>
}