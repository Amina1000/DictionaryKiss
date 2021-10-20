package com.cocos.develop.dictionarykiss.ui.main

import com.cocos.develop.dictionarykiss.data.DataModel
import com.cocos.develop.dictionarykiss.domain.AppState
import com.cocos.develop.dictionarykiss.domain.Repository
import com.cocos.develop.dictionarykiss.domain.RepositoryLocal
import com.cocos.develop.dictionarykiss.ui.viewModel.Interactor

/**
 * homework com.cocos.develop.dictionarykiss.ui.main
 *
 * @author Amina
 * 26.08.2021
 */
class MainInteractor (
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState>  {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(repositoryRemote.getData(word))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = AppState.Success(repositoryLocal.getData(word))
        }
        return appState
    }
}
