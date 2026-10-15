package com.quinceye.app.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sales")
data class Sale(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val clientId: Long? = null,
    val date: Long = System.currentTimeMillis(),
    val total: Double = 0.0
)
