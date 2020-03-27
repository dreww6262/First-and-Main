package com.example.firstandmainwilliamson


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [StoreItem::class], version = 1, exportSchema = false)
//@TypeConverters(DateConverter::class)
abstract class StoreRoomDatabase: RoomDatabase(){
    abstract fun storeDao(): StoreItemDao

    companion object {
        @Volatile
        private var INSTANCE: StoreRoomDatabase? = null

        fun getDatabase(
            context: Context
        ): StoreRoomDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StoreRoomDatabase::class.java,
                    "Movie_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}