package com.cocos.develop.dictionarykiss.di

import androidx.room.Room
import com.cocos.develop.dictionarykiss.ui.main.MainInteractor
import com.cocos.develop.dictionarykiss.ui.main.MainViewModel
import com.cocos.develop.repository.datasource.RetrofitImplementation
import com.cocos.develop.repository.datasource.RoomDataBaseImplementation
import com.cocos.develop.repository.RepositoryImplementation
import com.cocos.develop.repository.RepositoryImplementationLocal
import com.cocos.develop.repository.room.HistoryDataBase
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

/**
 * homework com.cocos.develop.dictionarykiss.di
 *
 * @author Amina
 * 07.09.2021
 */
fun injectDependencies() = loadModules

private val loadModules by lazy {
    loadKoinModules(listOf(application, mainScreen))
}

val application = module {

    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<com.cocos.develop.repository.domain.Repository<List<com.cocos.develop.model.data.DataModel>>> {
        RepositoryImplementation(
            RetrofitImplementation()
        )
    }
    single<com.cocos.develop.repository.domain.RepositoryLocal<List<com.cocos.develop.model.data.DataModel>>> {
        RepositoryImplementationLocal(
            RoomDataBaseImplementation(
                get()
            )
        )
    }
}

val mainScreen = module {
    factory { MainInteractor(get(), get()) }
    factory { MainViewModel(get()) }
}

