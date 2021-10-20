package com.cocos.develop.favoritescreen.ui

import androidx.lifecycle.LiveData
import com.cocos.develop.core.viewModel.BaseViewModel
import com.cocos.develop.favoritescreen.utils.parseSearchResults
import com.cocos.develop.model.data.AppState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * homework com.cocos.develop.dictionarykiss.ui.favorite
 *
 * @author Amina
 * 15.09.2021
 */
class FavoriteViewModel(private val interactor: FavoriteInteractor) : BaseViewModel<AppState>() {

    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        // Запускаем корутину для асинхронного доступа к серверу с помощью
        // launch
        viewModelCoroutineScope.launch { startInteractor(word, false) }
    }

    override fun handleError(error: Throwable) {
       _mutableLiveData.postValue(AppState.Error(error))
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) = withContext(Dispatchers.IO) {
        _mutableLiveData.postValue(
            parseSearchResults(
                interactor.getData(
                    word,
                    isOnline
                )
            )
        )
    }
}