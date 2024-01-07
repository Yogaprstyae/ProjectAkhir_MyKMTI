package com.example.mykmti.repository

import com.example.mykmti.data.Anggota
import kotlinx.coroutines.flow.Flow

interface RepositoryAnggota {
    fun getAllAnggotaStream(): Flow<List<Anggota>>

    fun getAnggotaStream(id: Int): Flow<Anggota?>

    suspend fun insertAnggota(anggota: Anggota)

    suspend fun deleteAnggota(anggota: Anggota)

    suspend fun updateAnggota(anggota:Anggota)
}