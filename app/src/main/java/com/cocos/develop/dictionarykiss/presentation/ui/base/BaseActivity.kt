package com.cocos.develop.dictionarykiss.presentation.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cocos.develop.dictionarykiss.domain.AppState

/**
 * homework com.cocos.develop.dictionarykiss.presentation.ui.base
 *
 * @author Amina
 * 25.08.2021
 */
abstract class BaseActivity<T : AppState, I : Interactor<T>> : AppCompatActivity() {

    abstract val model: BaseViewModel<T>

    abstract fun renderData(dataModel: T)
}
