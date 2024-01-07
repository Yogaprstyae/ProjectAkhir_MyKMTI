package com.example.mykmti.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AnggotaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(anggota: Anggota)

    @Update
    suspend fun update(anggota: Anggota)

    @Update
    suspend fun delete(anggota: Anggota)

    @Query("SELECT * from tblAnggota WHERE id = :id")
    fun getAnggota(id: Int): Flow<Anggota>

    @Query("SELECT * from tblAnggota ORDER BY nama ASC")
    fun getALlAnggota(): Flow<List<Anggota>>
}