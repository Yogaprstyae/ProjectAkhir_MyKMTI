package com.example.mykmti.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblAnggota")
data class Anggota(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val nama : String,
    val divisi : String,
    val telpon : String,
    val namaKeg : String,
    val tglKeg : String,
    val dana : String
)

//enum class Divisi {
//    PH,
//    KASTRAD,
//    HUMAS,
//    MEDPRO,
//    IPTEK,
//    KH,
//    SBO,
//    KWU
//}
//
//fun getDivisiList(): List<Divisi> {
//    val recurrenceList = mutableListOf<Divisi>()
//    recurrenceList.add(Divisi.PH)
//    recurrenceList.add(Divisi.KASTRAD)
//    recurrenceList.add(Divisi.HUMAS)
//    recurrenceList.add(Divisi.MEDPRO)
//    recurrenceList.add(Divisi.IPTEK)
//    recurrenceList.add(Divisi.KH)
//    recurrenceList.add(Divisi.SBO)
//    recurrenceList.add(Divisi.KWU)
//
//    return recurrenceList
//}


