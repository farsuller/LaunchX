package com.solodev.launchx.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.solodev.launchx.domain.model.entity.CrewEntity
import com.solodev.launchx.domain.model.entity.LandPadEntity
import com.solodev.launchx.domain.model.entity.RocketEntity

@Database(entities = [RocketEntity::class, CrewEntity::class, LandPadEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun launchXDao(): LaunchXDao

    companion object {
        const val DATABASE_NAME = "launchx.db"
    }
}
