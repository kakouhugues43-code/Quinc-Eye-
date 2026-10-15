package com.quinceye.app.data.dao

import androidx.room.*
import com.quinceye.app.data.entities.Sale

@Dao
interface SaleDao {
    @Insert
    suspend fun insert(sale: Sale): Long

    @Query("SELECT COUNT(*) FROM sales")
    suspend fun count(): Int

    @Query("SELECT SUM(total) FROM sales")
    suspend fun totalRevenue(): Double?
}
