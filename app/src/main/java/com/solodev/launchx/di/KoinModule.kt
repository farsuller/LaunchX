package com.solodev.launchx.di

import androidx.room.Room
import com.solodev.launchx.data.local.AppDatabase
import com.solodev.launchx.data.remote.SpaceXApi
import com.solodev.launchx.data.repository.LaunchXRepositoryImpl
import com.solodev.launchx.domain.repository.LaunchXRepository
import com.solodev.launchx.domain.usecase.GetCrews
import com.solodev.launchx.domain.usecase.GetLandPads
import com.solodev.launchx.domain.usecase.LaunchXUseCase
import com.solodev.launchx.domain.usecase.GetRockets
import com.solodev.launchx.presentation.screen.home.HomeViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {
    single {
        HttpClient(CIO) {
            install(ContentNegotiation){
                json(Json{
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }

            install(HttpTimeout){
                requestTimeoutMillis = 15000
                connectTimeoutMillis = 15000
                socketTimeoutMillis = 15000
            }

            defaultRequest {
                url("https://api.spacexdata.com/v4/")
            }
        }
    }

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "launchx.db").build()
    }
    single { get<AppDatabase>().launchXDao() }
    single<SpaceXApi> { SpaceXApi(get()) }
    single<LaunchXRepository> { LaunchXRepositoryImpl( get(), get()) }
    single { GetRockets( get()) }
    single { GetCrews(get()) }
    single { GetLandPads(get()) }
    single { LaunchXUseCase(get(), get(), get()) }
    factory {
        HomeViewModel( launchXUseCase = get())
    }
}


fun initializeKoin(){
    startKoin {
        modules(appModule)
    }
}

