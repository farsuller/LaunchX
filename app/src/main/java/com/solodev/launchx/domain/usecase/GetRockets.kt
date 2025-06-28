package com.solodev.launchx.domain.usecase

import com.solodev.launchx.data.RequestState
import com.solodev.launchx.domain.model.Rocket
import com.solodev.launchx.domain.repository.RocketsRepository
import kotlinx.coroutines.flow.Flow

class GetRockets(
    private val rocketRepository: RocketsRepository
) {
    suspend operator fun invoke() : Flow<RequestState<List<Rocket>>> = rocketRepository.getRockets()
}