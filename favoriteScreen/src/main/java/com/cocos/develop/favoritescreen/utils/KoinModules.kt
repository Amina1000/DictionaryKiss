package com.cocos.develop.favoritescreen.utils

import com.cocos.develop.favoritescreen.ui.FavoriteActivity
import com.cocos.develop.favoritescreen.ui.FavoriteInteractor
import com.cocos.develop.favoritescreen.ui.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun injectDependencies() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(listOf(favoriteScreen))
}

val favoriteScreen = module {
    scope(named<FavoriteActivity>()) {
        scoped { FavoriteInteractor(get()) }
        viewModel { FavoriteViewModel(get()) }
    }
//    factory { FavoriteViewModel(get()) }
//    factory { FavoriteInteractor(get()) }
}