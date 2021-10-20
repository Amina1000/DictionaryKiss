package com.cocos.develop.dictionarykiss.di

import com.cocos.develop.dictionarykiss.data.DataModel
import com.cocos.develop.dictionarykiss.data.datasource.RetrofitImplementation
import com.cocos.develop.dictionarykiss.data.datasource.RoomDataBaseImplementation
import com.cocos.develop.dictionarykiss.data.repository.RepositoryImplementation
import com.cocos.develop.dictionarykiss.domain.Repository
import com.cocos.develop.dictionarykiss.ui.main.MainInteractor
import com.cocos.develop.dictionarykiss.ui.main.MainViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * homework com.cocos.develop.dictionarykiss.di
 *
 * @author Amina
 * 07.09.2021
 */
val application = module {
    single<Repository<List<DataModel>>>(named(NAME_REMOTE)) { RepositoryImplementation(
        RetrofitImplementation()
    ) }
    single<Repository<List<DataModel>>>(named(NAME_LOCAL)) { RepositoryImplementation(
        RoomDataBaseImplementation()
    ) }
}

val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainViewModel(get()) }
}
