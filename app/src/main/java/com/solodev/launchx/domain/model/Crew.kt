package com.solodev.launchx.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Crew(
    val agency: String? = null,
    val id: String? = null,
    val image: String? = null,
    val launches: List<String>? = null,
    val name: String? = null,
    val status: String? = null,
    val wikipedia: String? = null
)