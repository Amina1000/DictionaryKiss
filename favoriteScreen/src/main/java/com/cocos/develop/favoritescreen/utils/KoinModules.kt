package com.cocos.develop.favoritescreen.utils

import com.cocos.develop.favoritescreen.ui.FavoriteInteractor
import com.cocos.develop.favoritescreen.ui.FavoriteViewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun injectDependencies() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(listOf(favoriteScreen))
}

val favoriteScreen = module {
    factory { FavoriteViewModel(get()) }
    factory { FavoriteInteractor(get()) }
}
