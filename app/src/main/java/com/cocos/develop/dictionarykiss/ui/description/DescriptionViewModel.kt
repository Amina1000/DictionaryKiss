package com.cocos.develop.dictionarykiss.ui.description

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cocos.develop.core.viewModel.BaseViewModel
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
class DescriptionViewModel(private val interactor: DescriptionInteractor): BaseViewModel<AppState>(){

    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        // Запускаем корутину для асинхронного доступа к серверу с помощью
        // launch
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) =
        withContext(Dispatchers.IO) {
            _mutableLiveData.postValue(
                parseSearchResults(
                    interactor.getData(
                        word,
                        isOnline
                    )
                )
            )
        }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value = AppState.Success(null)
        super.onCleared()
    }

    fun setData(data: DataModel) {
        viewModelCoroutineScope.launch {
            withContext(Dispatchers.IO) {
                interactor.setData(data)
            }
        }
    }

}