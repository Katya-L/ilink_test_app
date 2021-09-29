package com.example.testapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Model::class], version = 1, exportSchema = false)
abstract class ImgDatabase: RoomDatabase() {

    abstract fun savedImgDao(): SavedImgDao

    companion object {
        @Volatile
        private var INSTANCE: ImgDatabase? = null

        fun getDatabase(context: Context): ImgDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ImgDatabase::class.java,
                    "img_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}