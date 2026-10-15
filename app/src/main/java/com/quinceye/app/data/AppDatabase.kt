package com.quinceye.app.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.quinceye.app.data.entities.Product
import com.quinceye.app.data.dao.ProductDao
import com.quinceye.app.data.entities.Client
import com.quinceye.app.data.entities.Sale
import com.quinceye.app.data.entities.SaleItem
import com.quinceye.app.data.dao.ClientDao
import com.quinceye.app.data.dao.SaleDao
import com.quinceye.app.data.dao.SaleItemDao

@Database(entities = [Product::class, Client::class, Sale::class, SaleItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun clientDao(): ClientDao
    abstract fun saleDao(): SaleDao
    abstract fun saleItemDao(): SaleItemDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "quinc_eye_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
