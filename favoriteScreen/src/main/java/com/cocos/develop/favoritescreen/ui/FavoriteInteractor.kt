package com.cocos.develop.favoritescreen.ui

import com.cocos.develop.core.viewModel.Interactor
import com.cocos.develop.model.data.DataModel
import com.cocos.develop.model.data.AppState
import com.cocos.develop.repository.domain.RepositoryLocal

/**
 * homework com.cocos.develop.dictionarykiss.ui.main
 *
 * @author Amina
 * 26.08.2021
 */
class FavoriteInteractor (
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(repositoryLocal.getFavorite())
    }
}
