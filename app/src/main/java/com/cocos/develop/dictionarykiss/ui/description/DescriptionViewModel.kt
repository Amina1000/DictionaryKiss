package com.cocos.develop.dictionarykiss.ui.description

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cocos.develop.dictionarykiss.utils.parseSearchResults
import com.cocos.develop.model.data.AppState
import com.cocos.develop.model.data.DataModel
import kotlinx.coroutines.*

/**
 * homework com.cocos.develop.dictionarykiss.ui.description
 *
 * @author Amina
 * 09.10.2021
 */
class DescriptionViewModel(private val interactor: DescriptionInteractor):
    ViewModel() {

    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
           //
        })
    fun setData(data: DataModel) {
        viewModelCoroutineScope.launch {
            withContext(Dispatchers.IO) {
                interactor.setData(data)
            }
        }
    }


}