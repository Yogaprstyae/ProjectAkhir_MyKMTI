package com.example.mykmti.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mykmti.repository.RepositoryAnggota
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryAnggota: RepositoryAnggota
): ViewModel(){
    private val anggotaId: Int = checkNotNull(savedStateHandle[DetailsDestination.siswaIdArg])
    val uiState: StateFlow<ItemDetailsUIState> =
        repositoryAnggota.getAnggotaStream(anggotaId)
            .filterNotNull()
            .map {
                ItemDetailsUIState(detailAnggota = it.toDetailAnggota())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIME_MILLIS),
                initialValue = ItemDetailsUIState()
            )
    suspend fun deleteItem(){
        repositoryAnggota.deleteAnggota(uiState.value.detailAnggota.toAnggota())
    }
    companion object{
        private const val TIME_MILLIS = 5_000L
    }
}

data class ItemDetailsUIState(
    val outOfStock: Boolean = true,
    val detailAnggota: DetailAnggota = DetailAnggota()
)