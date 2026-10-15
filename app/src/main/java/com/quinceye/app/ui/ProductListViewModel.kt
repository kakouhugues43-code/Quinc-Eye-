package com.quinceye.app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.quinceye.app.data.dao.ProductDao

class ProductListViewModel(private val dao: ProductDao) : ViewModel() {
    val products = dao.getAll().asLiveData()

    class Factory(private val dao: ProductDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProductListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ProductListViewModel(dao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
