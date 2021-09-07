package com.cocos.develop.dictionarykiss.ui.main

import androidx.lifecycle.LiveData
import com.cocos.develop.dictionarykiss.domain.AppState
import com.cocos.develop.dictionarykiss.ui.viewModel.BaseViewModel
import com.cocos.develop.dictionarykiss.utils.parseSearchResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * homework com.cocos.develop.dictionarykiss.ui.main
 *
 * @author Amina
 * 02.09.2021
 */
class MainViewModel @Inject constructor(private val interactor: MainInteractor) :
    BaseViewModel<AppState>() {

    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) = withContext(Dispatchers.IO) {
        _mutableLiveData.postValue(parseSearchResults(interactor.getData(word, isOnline)))
    }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value = AppState.Success(null)
        super.onCleared()
    }

}
