package com.grameen.light.core.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.grameen.light.core.local.entity.ComplaintEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ComplaintDao {
    @Query("SELECT * FROM complaints WHERE userId = :userId ORDER BY createdAtMillis DESC")
    fun observeByUser(userId: String): Flow<List<ComplaintEntity>>

    @Query("SELECT * FROM complaints ORDER BY createdAtMillis DESC")
    fun observeAll(): Flow<List<ComplaintEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ComplaintEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(items: List<ComplaintEntity>)

    @Query("SELECT * FROM complaints WHERE synced = 0")
    suspend fun getUnsynced(): List<ComplaintEntity>

    @Query("SELECT complaintId FROM complaints ORDER BY complaintId DESC LIMIT 1")
    suspend fun getLatestComplaintId(): String?
}
