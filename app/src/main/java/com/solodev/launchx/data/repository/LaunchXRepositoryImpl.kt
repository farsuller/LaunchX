package com.solodev.launchx.data.repository

import com.solodev.launchx.data.RequestState
import com.solodev.launchx.data.local.LaunchXDao
import com.solodev.launchx.data.remote.SpaceXApi
import com.solodev.launchx.domain.model.Crew
import com.solodev.launchx.domain.model.Landpad
import com.solodev.launchx.domain.model.Rocket
import com.solodev.launchx.domain.model.entity.toDomain
import com.solodev.launchx.domain.model.entity.toEntity
import com.solodev.launchx.domain.repository.LaunchXRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LaunchXRepositoryImpl(
    private val spaceXApi: SpaceXApi,
    private val launchXDao: LaunchXDao,
) : LaunchXRepository {
    override suspend fun getRockets(): Flow<RequestState<List<Rocket>>> = flow {
        try {
            val rockets = spaceXApi.getRockets()

            launchXDao.insertAllRocket(rockets.map { it.toEntity() })

            emit(RequestState.Success(rockets))

        } catch (_: Exception) {
            val cached = launchXDao.getAllRocket().map { it.toDomain() }
            if (cached.isNotEmpty()) {
                emit(RequestState.Success(cached))
            } else {
                emit(RequestState.Error("No data available offline"))
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getCrews(): Flow<RequestState<List<Crew>>> = flow {
        try {
            val crews = spaceXApi.getCrews()

            launchXDao.insertAllCrew(crews.map { it.toEntity() })

            emit(RequestState.Success(crews))
        } catch (_: Exception) {
            val cached = launchXDao.getAllCrew().map { it.toDomain() }
            if (cached.isNotEmpty()) {
                emit(RequestState.Success(cached))
            } else {
                emit(RequestState.Error("No data available offline"))
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getLandPads(): Flow<RequestState<List<Landpad>>> = flow {
        try {
            val landPads = spaceXApi.getLandpads()

            launchXDao.insertAllLandPad(landPads.map { it.toEntity() })

            emit(RequestState.Success(landPads))
        } catch (_: Exception) {
            val cached = launchXDao.getAllLandPad().map { it.toDomain() }
            if (cached.isNotEmpty()) {
                emit(RequestState.Success(cached))
            } else {
                emit(RequestState.Error("No data available offline"))
            }
        }
    }
}