package com.solodev.launchx.domain.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.solodev.launchx.domain.model.Images
import com.solodev.launchx.domain.model.Landpad

@Entity
data class LandPadEntity(
    @PrimaryKey val id: String,
    val name: String?,
    val fullName: String?,
    val image: String?,
    val type: String?,
    val region: String?,
    val locality: String?,
    val details: String?,
)

fun Landpad.toEntity() = LandPadEntity(
    id = id,
    name = name,
    fullName = fullName,
    image = images?.large?.firstOrNull(),
    type = type,
    region = region,
    locality = locality,
    details = details,
)

fun LandPadEntity.toDomain() = Landpad(
    id = id,
    name = name,
    fullName = fullName,
    images = image?.let { Images(large = listOf(it)) },
    type = type,
    region = region,
    locality = locality,
    details = details,
)
