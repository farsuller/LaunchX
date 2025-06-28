package com.solodev.launchx.domain.repository

import com.solodev.launchx.data.RequestState
import com.solodev.launchx.domain.model.Crew
import com.solodev.launchx.domain.model.Rocket
import kotlinx.coroutines.flow.Flow

interface LaunchXRepository {
    suspend fun getRockets() : Flow<RequestState<List<Rocket>>>
    suspend fun getCrews() : Flow<RequestState<List<Crew>>>
}