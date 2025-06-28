package com.solodev.launchx.di

import com.solodev.launchx.data.remote.SpaceXApi
import com.solodev.launchx.data.repository.RocketsRepositoryImpl
import com.solodev.launchx.domain.repository.RocketsRepository
import com.solodev.launchx.domain.usecase.GetRocketUseCase
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

    single<SpaceXApi> { SpaceXApi(get()) }
    single<RocketsRepository> { RocketsRepositoryImpl( get()) }
    single { GetRockets( get()) }

    single { GetRocketUseCase(get()) }
    factory {
        HomeViewModel( rocketUseCase = get())
    }
}


fun initializeKoin(){
    startKoin {
        modules(appModule)
    }
}

