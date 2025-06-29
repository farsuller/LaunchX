package com.solodev.launchx.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.solodev.launchx.domain.model.entity.CrewEntity
import com.solodev.launchx.domain.model.entity.LandPadEntity
import com.solodev.launchx.domain.model.entity.RocketEntity

@Dao
interface LaunchXDao {
    @Query("SELECT * FROM RocketEntity")
    suspend fun getAllRocket(): List<RocketEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRocket(rockets: List<RocketEntity>)

    @Query("SELECT * FROM CrewEntity")
    suspend fun getAllCrew(): List<CrewEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCrew(crews: List<CrewEntity>)

    @Query("SELECT * FROM LandPadEntity")
    suspend fun getAllLandPad(): List<LandPadEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllLandPad(crews: List<LandPadEntity>)
}