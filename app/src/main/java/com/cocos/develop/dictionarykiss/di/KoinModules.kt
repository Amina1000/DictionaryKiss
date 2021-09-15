package com.cocos.develop.dictionarykiss.di

import androidx.room.Room
import com.cocos.develop.favoritescreen.ui.FavoriteInteractor
import com.cocos.develop.favoritescreen.ui.FavoriteViewModel
import com.cocos.develop.dictionarykiss.ui.main.MainInteractor
import com.cocos.develop.dictionarykiss.ui.main.MainViewModel
import com.cocos.develop.repository.datasource.RetrofitImplementation
import com.cocos.develop.repository.datasource.RoomDataBaseImplementation
import com.cocos.develop.repository.RepositoryImplementation
import com.cocos.develop.repository.RepositoryImplementationLocal
import com.cocos.develop.repository.room.HistoryDataBase
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
val favoriteScreen = module {
    factory { FavoriteViewModel(get()) }
    factory { FavoriteInteractor(get()) }
}

