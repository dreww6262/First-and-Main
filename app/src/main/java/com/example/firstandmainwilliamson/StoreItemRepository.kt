package com.example.firstandmainwilliamson

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class StoreItemRepository(private val storeDao: StoreItemDao){

    val allStores: LiveData<List<StoreItem>> = storeDao.getAllStores()

    @WorkerThread
    fun insert(store: StoreItem){
        storeDao.insertStore(store)
    }

    @WorkerThread
    fun deleteAll(){
        storeDao.DeleteAll()
    }


}