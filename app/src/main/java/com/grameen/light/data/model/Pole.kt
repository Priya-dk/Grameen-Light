package com.grameen.light.data.model

data class Pole(
    val id: String,
    val location: String,
    val status: PoleStatus,
    val latitude: Double,
    val longitude: Double
)

enum class PoleStatus {
    WORKING,
    FUSED,
    DAYTIME_ON
}
