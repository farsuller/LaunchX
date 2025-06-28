package com.solodev.launchx.domain.repository

import com.solodev.launchx.data.RequestState
import com.solodev.launchx.domain.model.Rocket
import kotlinx.coroutines.flow.Flow

interface RocketsRepository {
    suspend fun getRockets() : Flow<RequestState<List<Rocket>>>
}