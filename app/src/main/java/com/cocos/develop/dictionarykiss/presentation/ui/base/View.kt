package com.cocos.develop.dictionarykiss.presentation.ui.base

import com.cocos.develop.dictionarykiss.domain.AppState

/**
 * homework com.cocos.develop.dictionarykiss.presentation.ui.base
 *
 * @author Amina
 * 25.08.2021
 */
interface View {
    fun renderData(appState: AppState)
}
