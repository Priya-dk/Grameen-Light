package com.grameen.light.data.model

data class User(
    val name: String,
    val mobileNumber: String,
    val totalReports: Int = 0
)
