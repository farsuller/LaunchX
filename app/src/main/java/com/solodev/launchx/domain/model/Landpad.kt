package com.solodev.launchx.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Landpad(
    val details: String? = null,

    @SerialName("full_name")
    val fullName: String? = null,

    val id: String,
    val images: Images? = null,

    @SerialName("landing_attempts")
    val landingAttempts: Int? = null,

    @SerialName("landing_successes")
    val landingSuccesses: Int? = null,

    val latitude: Double? = null,
    val launches: List<String>? = null,
    val locality: String? = null,
    val longitude: Double? = null,
    val name: String? = null,
    val region: String? = null,
    val status: String? = null,
    val type: String? = null,
    val wikipedia: String? = null,
)

@Serializable
data class Images(
    val large: List<String>? = null,
)