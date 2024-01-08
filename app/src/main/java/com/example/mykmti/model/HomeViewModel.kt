package com.example.mykmti.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mykmti.data.Anggota
import com.example.mykmti.repository.RepositoryAnggota
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(private val  repositoryAnggota: RepositoryAnggota): ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val homeUiState: StateFlow<HomeUiState> = repositoryAnggota.getAllAnggotaStream()
        .filterNotNull()
        .map { HomeUiState(listAnggota = it.toList()) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUiState()
        )

    data class HomeUiState(
        val listAnggota: List<Anggota> = listOf()
    )
}