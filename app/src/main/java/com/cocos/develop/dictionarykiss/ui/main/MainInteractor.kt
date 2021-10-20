package com.cocos.develop.dictionarykiss.ui.main

import com.cocos.develop.dictionarykiss.data.DataModel
import com.cocos.develop.dictionarykiss.di.NAME_LOCAL
import com.cocos.develop.dictionarykiss.di.NAME_REMOTE
import com.cocos.develop.dictionarykiss.domain.AppState
import com.cocos.develop.dictionarykiss.domain.Repository
import com.cocos.develop.dictionarykiss.ui.viewModel.Interactor
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named

/**
 * homework com.cocos.develop.dictionarykiss.ui.main
 *
 * @author Amina
 * 26.08.2021
 */
class MainInteractor @Inject constructor(
    @Named(NAME_REMOTE) val repositoryRemote: Repository<List<DataModel>>,
    @Named(NAME_LOCAL) val repositoryLocal: Repository<List<DataModel>>
) : Interactor<AppState>  {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            repositoryRemote.getData(word).map { AppState.Success(it) }
        } else {
            repositoryLocal.getData(word).map { AppState.Success(it) }
        }
    }
}
