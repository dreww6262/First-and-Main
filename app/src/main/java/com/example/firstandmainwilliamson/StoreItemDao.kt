package com.example.firstandmainwilliamson

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StoreItemDao{

    @Query("SELECT * FROM store_table order BY name DESC")
    fun getAllStores(): LiveData<List<StoreItem>>


    @Query ("DELETE FROM store_table")
    fun DeleteAll()

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertStore(movie: StoreItem)


    @Query("SELECT * FROM store_table order BY name ASC")
    fun getAllStoresAsc(): LiveData<List<StoreItem>>


}