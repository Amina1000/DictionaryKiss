package com.cocos.develop.dictionarykiss.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cocos.develop.dictionarykiss.domain.AppState
import kotlinx.coroutines.*

/**
 * homework com.cocos.develop.dictionarykiss.ui.base
 *
 * @author Amina
 * 02.09.2021
 */
abstract class BaseViewModel<T : AppState>(
    protected open val _mutableLiveData: MutableLiveData<T> = MutableLiveData()
    ) : ViewModel() {

        protected val viewModelCoroutineScope = CoroutineScope(
            Dispatchers.Main
                    + SupervisorJob()
                    + CoroutineExceptionHandler { _, throwable ->
                handleError(throwable)
            })

        override fun onCleared() {
            super.onCleared()
            cancelJob()
        }

        protected fun cancelJob() {
            viewModelCoroutineScope.coroutineContext.cancelChildren()
        }

        abstract fun getData(word: String, isOnline: Boolean)

        abstract fun handleError(error: Throwable)
}