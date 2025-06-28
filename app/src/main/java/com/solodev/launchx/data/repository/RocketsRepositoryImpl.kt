package com.solodev.launchx.data.repository

import com.solodev.launchx.data.remote.SpaceXApi
import com.solodev.launchx.data.RequestState
import com.solodev.launchx.domain.model.Rocket
import com.solodev.launchx.domain.repository.RocketsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RocketsRepositoryImpl(
    private val spaceXApi: SpaceXApi
) : RocketsRepository {
    override suspend fun getRockets(): Flow<RequestState<List<Rocket>>> = flow {
        try {
            emit(RequestState.Success(spaceXApi.getRockets().also { println("Repository Result : $it") }))
        } catch (e: Exception) {
            emit(RequestState.Error(e.message ?: "Unknown Error"))
            println("Repository Result : ${e.message}")
        }
    }.flowOn(Dispatchers.IO)
}