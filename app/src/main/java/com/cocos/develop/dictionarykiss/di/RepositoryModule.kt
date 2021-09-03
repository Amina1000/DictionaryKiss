package com.cocos.develop.dictionarykiss.di

import com.cocos.develop.dictionarykiss.data.DataModel
import com.cocos.develop.dictionarykiss.data.datasource.RetrofitImplementation
import com.cocos.develop.dictionarykiss.data.datasource.RoomDataBaseImplementation
import com.cocos.develop.dictionarykiss.data.repository.RepositoryImplementation
import com.cocos.develop.dictionarykiss.domain.DataSource
import com.cocos.develop.dictionarykiss.domain.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * homework com.cocos.develop.dictionarykiss.di
 *
 * @author Amina
 * 02.09.2021
 */
@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRepositoryRemote(@Named(NAME_REMOTE) dataSourceRemote: DataSource<List<DataModel>>): Repository<List<DataModel>> =
        RepositoryImplementation(dataSourceRemote)

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideRepositoryLocal(@Named(NAME_LOCAL) dataSourceLocal: DataSource<List<DataModel>>): Repository<List<DataModel>> =
        RepositoryImplementation(dataSourceLocal)

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideDataSourceRemote(): DataSource<List<DataModel>> =
        RetrofitImplementation()

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideDataSourceLocal(): DataSource<List<DataModel>> = RoomDataBaseImplementation()
}
