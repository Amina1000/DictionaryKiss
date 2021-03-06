/**
 * homework
 *
 * @author Amina
 * 14.09.2021
 */
import org.gradle.api.JavaVersion

object Config {
    const val application_id = "com.cocos.develop.dictionarykiss"
    const val compile_sdk = 31
    const val min_sdk = 23
    const val target_sdk = 31
    val java_version = JavaVersion.VERSION_1_8
}

object Releases {
    const val version_code = 11
    const val version_name = "11.0"
}

object Modules {
    const val app = ":app"
    const val core = ":core"
    const val model = ":model"
    const val repository = ":repository"
    const val utils = ":utils"
    //Features
    const val favoriteScreen = ":favoriteScreen"
}

object Versions {

    //Tools
    const val multidex = "1.0.3"

    //Design
    const val appcompat = "1.3.1"
    const val material = "1.4.0"
    const val constraintLayout = "2.1.0"
    const val swipereFresh = "1.1.0"

    //Kotlin
    const val core = "1.6.0"
    const val stdlib = "1.5.21"
    const val coroutinesCore = "1.5.0"
    const val coroutinesAndroid = "1.5.0"

    //Retrofit
    const val retrofit = "2.9.0"
    const val converterGson = "2.9.0"
    const val interceptor = "3.12.2"
    const val adapterCoroutines = "0.9.2"
    const val adapterRx = "1.0.0"

    //Koin
    const val koinAndroid = "2.0.1"
    const val koinViewModel = "2.0.1"

    //Glide
    const val glide = "4.12.0"
    const val glideCompiler = "4.12.0"

    //Picasso
    const val picasso = "2.71828"

    //Room
    const val roomKtx = "2.4.0-alpha04"
    const val runtime = "2.4.0-alpha04"
    const val roomCompiler = "2.4.0-alpha04"

    //Google Play
    const val googlePlayCore = "1.10.1"

    //Test
    const val jUnit = "4.13.2"
    const val testExt = "1.1.3"
    const val espressoCore = "3.4.0"
}


object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val swipere_fresh = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipereFresh}"
}

object Kotlin {
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.stdlib}"
    const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.converterGson}"
    const val adapter_coroutines =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.adapterCoroutines}"
    const val logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
    const val adapter_rx = "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:${Versions.adapterRx}"
}

object Koin {
    const val koin_android = "org.koin:koin-android:${Versions.koinAndroid}"
    const val koin_view_model = "org.koin:koin-android-viewmodel:${Versions.koinViewModel}"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glideCompiler}"
}

object Picasso {
    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.runtime}"
    const val compiler = "androidx.room:room-compiler:${Versions.roomCompiler}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.roomKtx}"
}

object TestImpl {
    const val junit = "junit:junit:${Versions.jUnit}"
    const val testExt = "androidx.test.ext:junit:${Versions.testExt}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}

object GooglePlay {
    const val googlePlayCore = "com.google.android.play:core:${Versions.googlePlayCore}"
}

object Tools {
    const val multidex = "com.android.support:multidex:${Versions.multidex}"
}