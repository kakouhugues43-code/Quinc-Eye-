package com.quinceye.app.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import com.quinceye.app.data.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM products ORDER BY name ASC")
    fun getAll(): Flow<List<Product>>

    @Query("SELECT * FROM products WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): Product?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: Product): Long

    @Update
    suspend fun update(product: Product)

    @Delete
    suspend fun delete(product: Product)

    @Query("SELECT COUNT(*) FROM products")
    suspend fun count(): Int

    @Query("UPDATE products SET quantity = :qty WHERE id = :id")
    suspend fun updateQuantity(id: Long, qty: Int)
}
