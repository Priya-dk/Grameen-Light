package com.grameen.light.core.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.grameen.light.core.local.entity.PoleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PoleDao {
    @Query("SELECT * FROM poles")
    fun observeAll(): Flow<List<PoleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(items: List<PoleEntity>)

    @Query("SELECT * FROM poles WHERE poleId = :poleId LIMIT 1")
    suspend fun getById(poleId: String): PoleEntity?
}
