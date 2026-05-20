package com.grameen.light.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.grameen.light.core.local.dao.ComplaintDao
import com.grameen.light.core.local.dao.PoleDao
import com.grameen.light.core.local.entity.ComplaintEntity
import com.grameen.light.core.local.entity.PoleEntity

@Database(
    entities = [PoleEntity::class, ComplaintEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun poleDao(): PoleDao
    abstract fun complaintDao(): ComplaintDao
}
