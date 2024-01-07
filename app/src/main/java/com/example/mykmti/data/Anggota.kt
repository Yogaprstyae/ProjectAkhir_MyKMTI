package com.example.mykmti.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblAnggota")
data class Anggota(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val nama : String,
    val divisi : String,
    val telpon : String
)
