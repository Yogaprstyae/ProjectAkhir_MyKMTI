package com.example.mykmti.halaman

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mykmti.model.EditViewModel
import com.example.mykmti.model.PenyediaViewModel
import com.example.mykmti.navigasi.AnggotaTopAppBar
import com.example.mykmti.navigasi.DestinasiNavigasi
import com.example.roomsiswa.R
import kotlinx.coroutines.launch

object ItemEditDestination : DestinasiNavigasi {
    override val route = "item_edit"
    override val titleRes = R.string.edit
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            AnggotaTopAppBar(
                title = stringResource(ItemEditDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        EntryAnggotaBody(
            uiStateAnggota = viewModel.anggotaUiState,
            onSiswaValueChange = viewModel :: updateUIState,
            onSaveClick = {

                coroutineScope.launch {
                    viewModel.updateAnggota()
                    navigateBack()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}