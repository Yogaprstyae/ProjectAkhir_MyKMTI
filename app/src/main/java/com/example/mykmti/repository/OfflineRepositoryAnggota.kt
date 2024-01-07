package com.example.mykmti.repository

import com.example.mykmti.data.Anggota
import com.example.mykmti.data.AnggotaDao
import kotlinx.coroutines.flow.Flow

class OfflineRepositoryAnggota(private val anggotaDao: AnggotaDao): RepositoryAnggota {

    override fun getAllAnggotaStream(): Flow<List<Anggota>> = anggotaDao.getALlAnggota()

    override fun getAnggotaStream(id: Int): Flow<Anggota?> = anggotaDao.getAnggota(id)

    override suspend fun insertAnggota(anggota: Anggota) = anggotaDao.insert(anggota)

    override suspend fun deleteAnggota(anggota: Anggota) = anggotaDao.delete(anggota)

    override suspend fun updateAnggota(anggota: Anggota) = anggotaDao.update(anggota)


}