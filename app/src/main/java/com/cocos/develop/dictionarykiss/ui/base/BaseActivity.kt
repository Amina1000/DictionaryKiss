package com.cocos.develop.dictionarykiss.ui.base

import androidx.appcompat.app.AppCompatActivity
import com.cocos.develop.dictionarykiss.domain.AppState
import com.cocos.develop.dictionarykiss.ui.viewModel.BaseViewModel
import com.cocos.develop.dictionarykiss.ui.viewModel.Interactor

/**
 * homework com.cocos.develop.dictionarykiss.ui.base
 *
 * @author Amina
 * 25.08.2021
 */
abstract class BaseActivity<T : AppState, I : Interactor<T>> : AppCompatActivity() {

    abstract val model: BaseViewModel<T>

    abstract fun renderData(dataModel: T)
}
