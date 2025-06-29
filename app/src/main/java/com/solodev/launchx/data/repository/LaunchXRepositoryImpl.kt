package com.solodev.launchx.data.repository

import com.solodev.launchx.data.remote.SpaceXApi
import com.solodev.launchx.data.RequestState
import com.solodev.launchx.domain.model.Crew
import com.solodev.launchx.domain.model.Landpad
import com.solodev.launchx.domain.model.Rocket
import com.solodev.launchx.domain.repository.LaunchXRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LaunchXRepositoryImpl(
    private val spaceXApi: SpaceXApi
) : LaunchXRepository {
    override suspend fun getRockets(): Flow<RequestState<List<Rocket>>> = flow {
        try {
            emit(RequestState.Success(spaceXApi.getRockets()))
        } catch (e: Exception) {
            emit(RequestState.Error(e.message ?: "Unknown Error"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getCrews(): Flow<RequestState<List<Crew>>> = flow {
        try {
            emit(RequestState.Success(spaceXApi.getCrews()))
        } catch (e: Exception) {
            emit(RequestState.Error(e.message ?: "Unknown Error"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getLandPads(): Flow<RequestState<List<Landpad>>> = flow {
        try {
            emit(RequestState.Success(spaceXApi.getLandpads()))
        } catch (e: Exception) {
            emit(RequestState.Error(e.message ?: "Unknown Error"))
        }
    }
}