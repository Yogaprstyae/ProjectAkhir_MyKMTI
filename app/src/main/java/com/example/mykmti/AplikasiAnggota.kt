package com.example.mykmti

import android.app.Application
import com.example.mykmti.repository.ContainerApp
import com.example.mykmti.repository.ContainerDataApp

class AplikasiAnggota : Application() {
    /**
     * AppContainer instance digunakan oleh kelas-kelas lainnya untuk mendapatkan dependensi
     */
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(this)
    }
}