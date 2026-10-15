package com.quinceye.app.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sale_items")
data class SaleItem(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val saleId: Long = 0,
    val productId: Long = 0,
    val qty: Int = 0,
    val price: Double = 0.0
)
