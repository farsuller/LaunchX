package com.solodev.launchx.domain.usecase

import com.solodev.launchx.data.RequestState
import com.solodev.launchx.domain.model.Rocket
import com.solodev.launchx.domain.repository.LaunchXRepository
import kotlinx.coroutines.flow.Flow

class GetRockets(
    private val repository: LaunchXRepository,
) {
    suspend operator fun invoke(): Flow<RequestState<List<Rocket>>> = repository.getRockets()
}
