package com.cocos.develop.dictionarykiss.ui.main

import com.cocos.develop.model.data.DataModel
import com.cocos.develop.model.data.AppState
import com.cocos.develop.repository.domain.Repository
import com.cocos.develop.repository.domain.RepositoryLocal
import com.cocos.develop.core.viewModel.Interactor

/**
 * homework com.cocos.develop.dictionarykiss.ui.main
 *
 * @author Amina
 * 26.08.2021
 */
class MainInteractor (
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(repositoryRemote.getData(word))
        } else {
            appState = AppState.Success(repositoryLocal.getData(word))
        }
        return appState
    }

}
