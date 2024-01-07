package com.example.mykmti.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Anggota::class], version = 1, exportSchema = false)
abstract class DatabaseAnggota : RoomDatabase(){
    abstract fun AnggotaDao() : AnggotaDao

    companion object {
        @Volatile
        private var Instance: DatabaseAnggota? = null

        fun getDatabase(context: Context): DatabaseAnggota{
            return (Instance?: synchronized(this){
                Room.databaseBuilder(context,
                    DatabaseAnggota::class.java,
                    "anggota_database")
                    .build().also { Instance=it}
            })
        }
    }
}