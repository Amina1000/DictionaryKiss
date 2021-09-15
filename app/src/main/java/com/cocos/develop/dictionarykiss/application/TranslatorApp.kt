package com.cocos.develop.dictionarykiss.application

import android.app.Application
import com.cocos.develop.dictionarykiss.di.application
import com.cocos.develop.dictionarykiss.di.favoriteScreen
import com.cocos.develop.dictionarykiss.di.mainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, favoriteScreen))
        }
    }
}
