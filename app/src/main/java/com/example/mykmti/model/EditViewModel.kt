package com.example.mykmti.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mykmti.halaman.ItemEditDestination
import com.example.mykmti.repository.RepositoryAnggota
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val  repositoryAnggota: RepositoryAnggota
) : ViewModel (){

    var anggotaUiState by mutableStateOf(UIStateAnggota())
        private set

    private val itemId: Int = checkNotNull(savedStateHandle[ItemEditDestination.itemIdArg])

    init {
        viewModelScope.launch {
            anggotaUiState = repositoryAnggota.getAnggotaStream(itemId)
                .filterNotNull()
                .first()
                .toUIStateAnggota(true)
        }
    }


    suspend fun updateAnggota() {
        if (validasiInput(anggotaUiState.detailAnggota)) {
            repositoryAnggota.updateAnggota(anggotaUiState.detailAnggota.toAnggota())
        }
        else {
            println("Data tidak valid")
        }
    }

    fun updateUIState(detailAnggota: DetailAnggota) {
        anggotaUiState =
            UIStateAnggota(detailAnggota = detailAnggota, isEntryValid = validasiInput(detailAnggota))
    }

    private fun validasiInput(uiState: DetailAnggota = anggotaUiState.detailAnggota ): Boolean {
        return with(uiState) {
            nama.isNotBlank()
                    && divisi.isNotBlank()
                    && telpon.isNotBlank()
                    && namaKeg.isNotBlank()
                    && tglKeg.isNotBlank()
                    && dana.isNotBlank()
        }
    }
}