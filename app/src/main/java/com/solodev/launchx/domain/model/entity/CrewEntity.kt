package com.solodev.launchx.domain.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.solodev.launchx.domain.model.Crew

@Entity
data class CrewEntity(
    @PrimaryKey val id: String,
    val name: String?,
    val image: String?,
)

fun Crew.toEntity() = CrewEntity(
    id = id,
    name = name,
    image = image,
)

fun CrewEntity.toDomain() = Crew(
    id = id,
    name = name,
    image = image,
)
