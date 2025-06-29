package com.solodev.launchx.domain.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.solodev.launchx.domain.model.Rocket

@Entity
data class RocketEntity(
    @PrimaryKey val id: String,
    val name: String?,
    val country: String?,
    val company: String?,
    val firstFlight: String?,
    val description: String?,
    val image: String?
)

fun Rocket.toEntity() = RocketEntity(
    id = id,
    name = name,
    country = country,
    company = company,
    firstFlight = firstFlight,
    description = description,
    image = flickrImages?.firstOrNull()
)


fun RocketEntity.toDomain() = Rocket(
    id = id,
    name = name,
    country = country,
    company = company,
    firstFlight = firstFlight,
    description = description,
    flickrImages = image?.let { listOf(it) },
)
