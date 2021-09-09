package com.ooooonly.onote.buildSrc

// i mplementation "(.*:(.*):.*)"
// const val $2 = "$1"

object Versions {
    const val kotlin = "1.5.21"
    const val compose = "1.0.1"
    const val coroutines = "1.5.0"
    const val accompanist = "0.17.0"
    const val room = "2.3.0"
    const val appCenter = "4.2.0"
    const val okhttp = "4.9.1"
    const val hilt = "2.38.1"
    const val lifecycle = "2.3.1"
    const val coil = "1.3.2"
}

object Libs {
    object Kotlin {
        const val std = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"

        object Serialization {
            const val json = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2"
        }

        object Coroutine {
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        }
    }

    object Accompanist {
        const val swipeRefresh = "com.google.accompanist:accompanist-swiperefresh:${Versions.accompanist}"
        const val insets = "com.google.accompanist:accompanist-insets:${Versions.accompanist}"
        const val systemUiController = "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}"
        const val flowlayout = "com.google.accompanist:accompanist-flowlayout:${Versions.accompanist}"
        const val permissions = "com.google.accompanist:accompanist-permissions:0.16.0"
        const val placeholderMaterial = "com.google.accompanist:accompanist-placeholder-material:0.16.0"
    }
    
    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.3.0"
        const val activityKtx = "androidx.activity:activity-ktx:1.3.1"
        const val coreKtx = "androidx.core:core-ktx:1.6.0"
        const val preference = "androidx.preference:preference:1.1.1"
        
        object LifeCycle {
            const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
            const val viewmodelSavedstate = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}"
            const val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
            const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07"
        }

        object Test {
            const val junit = "androidx.test.ext:junit:1.1.3"
            const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
            const val rules = "androidx.test:rules:1.4.0"
            const val runner = "androidx.test:runner:1.4.0"
        }
    }
    
    object Compose {
        const val navigation = "androidx.navigation:navigation-compose:2.4.0-alpha07"
        const val activity = "androidx.activity:activity-compose:1.3.1"

        const val runtime = "androidx.compose.runtime:runtime:${Versions.compose}"
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val foundationLayout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
        const val material = "androidx.compose.material:material:${Versions.compose}"
        const val materialIconsExtended = "androidx.compose.material:material-icons-extended:${Versions.compose}"
        const val foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
        const val animation = "androidx.compose.animation:animation:${Versions.compose}"
        const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"

        object UiTest {
            const val junit4 = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
            const val uiTest = "androidx.compose.ui:ui-test:${Versions.compose}"
            const val manifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
        }
    }

    object Hilt {
        const val android = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val androidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    }
    
    object Coil {
        const val coil = "io.coil-kt:coil:${Versions.coil}"
        const val coilCompose = "io.coil-kt:coil-compose:${Versions.coil}"
    }

    object Okhttp {
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
        const val coroutines = "ru.gildor.coroutines:kotlin-coroutines-okhttp:1.0"
    }

    object DataStore {
        const val preferences = "androidx.datastore:datastore-preferences:1.0.0-rc02"
    }

    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.room}"
        const val compiler = "androidx.room:room-compiler:${Versions.room}"
        const val ktx = "androidx.room:room-ktx:${Versions.room}"
    }

    object AppCenter {
        const val analytics = "com.microsoft.appcenter:appcenter-analytics:${Versions.appCenter}"
        const val crashes = "com.microsoft.appcenter:appcenter-crashes:${Versions.appCenter}"
        const val distribute = "com.microsoft.appcenter:appcenter-distribute:${Versions.appCenter}"
    }

    const val material = "com.google.android.material:material:1.4.0"
    const val junit = "junit:junit:4.13.2"
}