package com.example.mykmti.model

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mykmti.AplikasiAnggota

object PenyediaViewModel{
    val Factory = viewModelFactory {

        initializer {
            HomeViewModel(aplikasiAnggota().container.repositoryAnggota)
        }

        initializer {
            EntryViewModel(aplikasiAnggota().container.repositoryAnggota)
        }
        initializer {
            DetailViewModel(
                createSavedStateHandle(),
                aplikasiAnggota().container.repositoryAnggota,
            )
        }
        initializer {
            EditViewModel(
                createSavedStateHandle(),
                aplikasiAnggota().container.repositoryAnggota
            )
        }
    }
}

fun CreationExtras.aplikasiAnggota():AplikasiAnggota =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiAnggota)