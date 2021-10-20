package com.cocos.develop.dictionarykiss.presentation.ui.main

import com.cocos.develop.dictionarykiss.data.DataModel
import com.cocos.develop.dictionarykiss.domain.AppState
import com.cocos.develop.dictionarykiss.domain.Repository
import com.cocos.develop.dictionarykiss.presentation.ui.base.Interactor
import io.reactivex.Observable

/**
 * homework com.cocos.develop.dictionarykiss.presentation.ui.main
 *
 * @author Amina
 * 26.08.2021
 */
class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}
