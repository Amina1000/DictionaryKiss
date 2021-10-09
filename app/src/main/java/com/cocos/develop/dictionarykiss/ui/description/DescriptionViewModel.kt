package com.cocos.develop.dictionarykiss.ui.description

import androidx.lifecycle.ViewModel
import com.cocos.develop.model.data.DataModel
import kotlinx.coroutines.*

/**
 * homework com.cocos.develop.dictionarykiss.ui.description
 *
 * @author Amina
 * 09.10.2021
 */
class DescriptionViewModel(private val interactor: DescriptionInteractor): ViewModel()  {

    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            //обработать ошибку
        })
    fun setData(data: DataModel) {
        viewModelCoroutineScope.launch {
            withContext(Dispatchers.IO) {
                interactor.setData(data)
            }
        }
    }


}