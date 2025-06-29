package com.solodev.launchx.data.remote

import com.solodev.launchx.domain.model.Crew
import com.solodev.launchx.domain.model.Landpad
import com.solodev.launchx.domain.model.Rocket
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class SpaceXApi(
    private val client: HttpClient
) {
    suspend fun getRockets() : List<Rocket> {
        return client.get("rockets").body()
    }

    suspend fun getCrews() : List<Crew> {
        return client.get("crew").body()
    }

    suspend fun getLandpads() : List<Landpad> {
        return client.get("landpads").body()
    }
}