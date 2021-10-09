package com.cocos.develop.dictionarykiss.ui.description

import com.cocos.develop.core.viewModel.Interactor
import com.cocos.develop.model.data.AppState
import com.cocos.develop.model.data.DataModel
import com.cocos.develop.repository.domain.RepositoryLocal

/**
 * homework com.cocos.develop.dictionarykiss.ui.description
 *
 * @author Amina
 * 09.10.2021
 */
class DescriptionInteractor(
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState>{

    suspend fun setData(dataModel: DataModel) {
        repositoryLocal.saveToDB(dataModel)
    }

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(repositoryLocal.getFavorite())
    }
}
