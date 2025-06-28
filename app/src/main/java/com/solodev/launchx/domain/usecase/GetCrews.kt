package com.solodev.launchx.domain.usecase

import com.solodev.launchx.data.RequestState
import com.solodev.launchx.domain.model.Crew
import com.solodev.launchx.domain.repository.LaunchXRepository
import kotlinx.coroutines.flow.Flow

class GetCrews(
    private val repository: LaunchXRepository
) {
    suspend operator fun invoke() : Flow<RequestState<List<Crew>>> = repository.getCrews()
}