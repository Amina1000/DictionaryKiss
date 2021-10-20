package com.cocos.develop.dictionarykiss.application

import android.app.Application
import com.cocos.develop.dictionarykiss.di.application
import com.cocos.develop.dictionarykiss.di.mainScreen
import org.koin.core.context.startKoin

class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}
