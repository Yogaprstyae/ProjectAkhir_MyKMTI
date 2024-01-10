package com.example.mykmti.halaman

import com.example.mykmti.model.DetailAnggota
import com.example.mykmti.model.EntryViewModel
import com.example.mykmti.model.PenyediaViewModel
import com.example.mykmti.model.UIStateAnggota
import com.example.mykmti.navigasi.AnggotaTopAppBar
import com.example.mykmti.navigasi.DestinasiNavigasi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mykmti.R
import kotlinx.coroutines.launch

object DestinasiEntry: DestinasiNavigasi {
    override val route = "item_entry"
    override val titleRes = R.string.entry_anggota
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryAnggotaScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EntryViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AnggotaTopAppBar(
                title = stringResource(DestinasiEntry.titleRes),
                canNavigateBack = true,
                scrollBehavior = scrollBehavior)
        })
    { innerPadding ->
        EntryAnggotaBody(
            uiStateAnggota = viewModel.uiStateAnggota,
            onSiswaValueChange = viewModel::updateUIState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveAnggota()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun EntryAnggotaBody(
    uiStateAnggota: UIStateAnggota,
    onSiswaValueChange: (DetailAnggota) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column (
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
    ){
        FormInputAnggota(
            detailAnggota = uiStateAnggota.detailAnggota,
            onValueChange =  onSiswaValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            enabled = uiStateAnggota.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.btn_submit))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputAnggota(
    detailAnggota: DetailAnggota,
    modifier: Modifier = Modifier,
    onValueChange: (DetailAnggota) -> Unit = {},
    enabled: Boolean = true
){
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ){
        OutlinedTextField(
            value = detailAnggota.nama,
            onValueChange = {onValueChange(detailAnggota.copy(nama = it))},
            label = { Text("Nama Yang Mengajukan")},
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailAnggota.divisi,
            onValueChange = {onValueChange(detailAnggota.copy(divisi = it))},
            label = { Text("Divisi Yang Mengajukan")},
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailAnggota.telpon,
            onValueChange = {onValueChange(detailAnggota.copy(telpon = it))},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text("Telepon")},
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailAnggota.namaKeg,
            onValueChange = {onValueChange(detailAnggota.copy(namaKeg = it))},
            label = { Text("Nama Kegiatan Yang Akan Dilaksanakan")},
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailAnggota.desKeg,
            onValueChange = {onValueChange(detailAnggota.copy(desKeg = it))},
            label = { Text("Deskripsi kan Kegiatan Secara Detail")},
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailAnggota.tglKeg,
            onValueChange = {onValueChange(detailAnggota.copy(tglKeg = it))},
            trailingIcon = {
                           IconButton(onClick = {}) {
                               Icon(
                                  imageVector = Icons.Default.DateRange,
                                   contentDescription = "Date Picker"
                               )
                           }
            },
            label = { Text("Tanggal Berlangsung nya Kegiatan")},
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailAnggota.dana,
            onValueChange = {onValueChange(detailAnggota.copy(divisi = it))},
            label = { Text("Total Dana Yang Diminta")},
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        if (enabled){
            Text(
                text = stringResource(R.string.required_field),
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium))
            )
        }
        Divider(
            thickness = dimensionResource(R.dimen.padding_small),
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
        )
    }
}