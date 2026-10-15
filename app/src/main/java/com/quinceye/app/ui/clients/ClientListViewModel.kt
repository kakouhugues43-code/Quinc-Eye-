package com.quinceye.app.ui.clients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.quinceye.app.data.dao.ClientDao

class ClientListViewModel(private val dao: ClientDao) : ViewModel() {
    val clients = dao.getAll().asLiveData()

    class Factory(private val dao: ClientDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ClientListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ClientListViewModel(dao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
