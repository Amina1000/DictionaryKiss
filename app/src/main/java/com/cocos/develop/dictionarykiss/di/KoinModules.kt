package com.cocos.develop.dictionarykiss.di

import androidx.room.Room
import com.cocos.develop.dictionarykiss.data.DataModel
import com.cocos.develop.dictionarykiss.data.datasource.RetrofitImplementation
import com.cocos.develop.dictionarykiss.data.datasource.RoomDataBaseImplementation
import com.cocos.develop.dictionarykiss.data.repository.RepositoryImplementation
import com.cocos.develop.dictionarykiss.data.repository.RepositoryImplementationLocal
import com.cocos.develop.dictionarykiss.data.room.HistoryDataBase
import com.cocos.develop.dictionarykiss.domain.Repository
import com.cocos.develop.dictionarykiss.domain.RepositoryLocal
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

    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<DataModel>>> {
        RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }
}

val mainScreen = module {
    factory { MainInteractor(get(), get()) }
    factory { MainViewModel(get()) }
}

