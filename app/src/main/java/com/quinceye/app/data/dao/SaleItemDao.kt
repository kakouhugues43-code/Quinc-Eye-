package com.quinceye.app.data.dao

import androidx.room.*
import com.quinceye.app.data.entities.SaleItem

@Dao
interface SaleItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: SaleItem): Long

    @Query("SELECT * FROM sale_items WHERE saleId = :saleId")
    suspend fun itemsForSale(saleId: Long): List<SaleItem>
}
