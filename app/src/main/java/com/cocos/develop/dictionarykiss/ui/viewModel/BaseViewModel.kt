package com.cocos.develop.dictionarykiss.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cocos.develop.dictionarykiss.domain.AppState
import com.cocos.develop.dictionarykiss.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * homework com.cocos.develop.dictionarykiss.ui.base
 *
 * @author Amina
 * 02.09.2021
 */
abstract class BaseViewModel<T : AppState>(
) : ViewModel() {

    protected val liveDataForViewToObserve: MutableLiveData<T> = MutableLiveData()
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()
    protected val schedulerProvider: SchedulerProvider = SchedulerProvider()

    // Метод, благодаря которому Activity подписывается на изменение данных,
    // возвращает LiveData, через которую и передаются данные
    open fun getData(word: String, isOnline: Boolean): LiveData<T> = liveDataForViewToObserve
    // Единственный метод класса ViewModel, который вызывается перед
    // уничтожением Activity
    override fun onCleared() {
        compositeDisposable.clear()
    }
}