package com.example.firstandmainwilliamson

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "store_table")
data class StoreItem(@PrimaryKey @ColumnInfo(name ="id") var id: Long,
                     @ColumnInfo(name="name") var name: String,
                     @ColumnInfo(name="description") var description: String
)
