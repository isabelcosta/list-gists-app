package com.isabelcmdcosta.listgists.data.database

import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.content.Context
import com.isabelcmdcosta.listgists.data.models.Gist

@Database(entities = [Gist::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gistDao(): GistDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(context,
                            AppDatabase::class.java, "gist.db")
                            .build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }
}
