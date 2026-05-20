package com.grameen.light.core.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "poles")
data class PoleEntity(
    @PrimaryKey val poleId: String,
    val sector: String,
    val controller: String,
    val latitude: Double,
    val longitude: Double,
    val status: String,
    val lastReportedMillis: Long
)
