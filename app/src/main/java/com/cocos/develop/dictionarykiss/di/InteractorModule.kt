package com.cocos.develop.dictionarykiss.di

import com.cocos.develop.dictionarykiss.data.DataModel
import com.cocos.develop.dictionarykiss.domain.Repository
import com.cocos.develop.dictionarykiss.ui.main.MainInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * homework com.cocos.develop.dictionarykiss.di
 *
 * @author Amina
 * 02.09.2021
 */
@Module
class InteractorModule {

    @Provides
    internal fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: Repository<List<DataModel>>,
        @Named(NAME_LOCAL) repositoryLocal: Repository<List<DataModel>>
    ) = MainInteractor(repositoryRemote, repositoryLocal)
}
