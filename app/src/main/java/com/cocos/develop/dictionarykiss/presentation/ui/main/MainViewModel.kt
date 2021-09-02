package com.cocos.develop.dictionarykiss.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cocos.develop.dictionarykiss.data.datasource.DataSourceLocal
import com.cocos.develop.dictionarykiss.data.datasource.DataSourceRemote
import com.cocos.develop.dictionarykiss.data.repository.RepositoryImplementation
import com.cocos.develop.dictionarykiss.domain.AppState
import com.cocos.develop.dictionarykiss.presentation.ui.base.BaseViewModel
import io.reactivex.observers.DisposableObserver

/**
 * homework com.cocos.develop.dictionarykiss.presentation.ui.main
 *
 * @author Amina
 * 02.09.2021
 */
class MainViewModel(
    private val interactor: MainInteractor = MainInteractor(
        RepositoryImplementation(DataSourceRemote()),
        RepositoryImplementation(DataSourceLocal())
    )
) : BaseViewModel<AppState>() {

    private var appState: AppState? = null

    override fun getData(word: String, isOnline: Boolean): LiveData<AppState> {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { liveDataForViewToObserve.value = AppState.Loading(null) }
                .subscribeWith(getObserver())
        )
        return super.getData(word, isOnline)
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {

            override fun onNext(state: AppState) {
                appState = state
                liveDataForViewToObserve.value = state
            }

            override fun onError(e: Throwable) {
                liveDataForViewToObserve.value = AppState.Error(e)
            }

            override fun onComplete() {
            }
        }
    }
}
