package com.cocos.develop.dictionarykiss.presentation.ui.base

import com.cocos.develop.dictionarykiss.domain.AppState

/**
 * homework com.cocos.develop.dictionarykiss.domain.presenter
 *
 * @author Amina
 * 26.08.2021
 */
interface Presenter<T : AppState, V : View> {

    fun attachView(view: V)

    fun detachView(view: V)

    fun getData(word: String, isOnline: Boolean)
}
