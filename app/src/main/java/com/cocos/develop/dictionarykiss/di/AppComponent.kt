package com.cocos.develop.dictionarykiss.di

import android.app.Application
import com.cocos.develop.dictionarykiss.application.TranslatorApp
import com.cocos.develop.dictionarykiss.ui.main.MainActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * homework com.cocos.develop.dictionarykiss.di
 *
 * @author Amina
 * 02.09.2021
 */
@Component(
    modules = [
        InteractorModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        ActivityModule::class,
        AndroidSupportInjectionModule::class]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(englishVocabularyApp: TranslatorApp)
}

