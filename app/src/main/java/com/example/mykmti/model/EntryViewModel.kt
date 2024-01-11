package com.example.mykmti.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mykmti.data.Anggota
import com.example.mykmti.repository.RepositoryAnggota

class EntryViewModel(private val repositoryAnggota: RepositoryAnggota): ViewModel(){
    var uiStateAnggota by mutableStateOf(UIStateAnggota())
        private set
    private fun validasiInput(uiState: DetailAnggota = uiStateAnggota.detailAnggota): Boolean {
        return with(uiState) {
            nama.isNotBlank()
                    && divisi.isNotBlank()
                    && telpon.isNotBlank()
                    && namaKeg.isNotBlank()
                    && desKeg.isNotBlank()
                    && tglKeg.isNotBlank()
        }
    }
    fun updateUIState(detailAnggota: DetailAnggota) {
        uiStateAnggota =
            UIStateAnggota(detailAnggota = detailAnggota, isEntryValid = validasiInput(detailAnggota))
    }
    suspend fun saveAnggota() {
        if (validasiInput()) {
            repositoryAnggota.insertAnggota(uiStateAnggota.detailAnggota.toAnggota())
        }
    }
}
data class UIStateAnggota(
    val detailAnggota: DetailAnggota = DetailAnggota(),
    val isEntryValid: Boolean = false
)
data class DetailAnggota(
    val id: Int = 0,
    val nama: String = "",
    val divisi: String = "",
    val telpon: String = "",
    val namaKeg : String = "",
    val desKeg : String = "",
    val tglKeg : String = "",
    val dana : String = ""
)
fun DetailAnggota.toAnggota(): Anggota = Anggota(
    id = id,
    nama = nama,
    divisi = divisi,
    telpon = telpon,
    namaKeg = namaKeg,
    desKeg = desKeg,
    tglKeg = tglKeg,
    dana = dana
)
fun Anggota.toUIStateAnggota(isEntryValid: Boolean = false): UIStateAnggota = UIStateAnggota(
    detailAnggota = this.toDetailAnggota(),
    isEntryValid = isEntryValid
)
fun Anggota.toDetailAnggota(): DetailAnggota = DetailAnggota(
    id = id,
    nama = nama,
    divisi = divisi,
    telpon = telpon,
    namaKeg = namaKeg,
    desKeg = desKeg,
    tglKeg = tglKeg,
    dana = dana
)