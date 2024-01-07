package com.example.mykmti.repository

import android.content.Context
import com.example.mykmti.data.DatabaseAnggota

interface ContainerApp{
    val repositoryAnggota: RepositoryAnggota
}

class ContainerDataApp(private val context: Context): ContainerApp{
    override val repositoryAnggota: RepositoryAnggota by lazy {
        OfflineRepositoryAnggota(DatabaseAnggota.getDatabase(context).AnggotaDao())
    }
}