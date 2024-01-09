package com.example.mykmti.halaman

import com.example.mykmti.data.Anggota
import com.example.mykmti.model.HomeViewModel
import com.example.mykmti.model.PenyediaViewModel
import com.example.mykmti.navigasi.AnggotaTopAppBar
import com.example.mykmti.navigasi.DestinasiNavigasi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roomsiswa.R

object DestinasiHome : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AnggotaTopAppBar(
                title = stringResource(DestinasiHome.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.entry_anggota)
                )
            }
        },
    ){
            innerPadding ->
        val  uiStateAnggota by viewModel.homeUiState.collectAsState()
        BodyHome(
            itemAnggota = uiStateAnggota.listAnggota,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            onAnggotaClick = onDetailClick
        )
    }
}

@Composable
fun BodyHome(
    itemAnggota: List<Anggota>,
    modifier: Modifier = Modifier,
    onAnggotaClick: (Int) -> Unit = {}
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (itemAnggota.isEmpty()){
            Text(
                text = stringResource(R.string.deskripsi_no_data),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            ListAnggota(
                itemAnggota = itemAnggota,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small)),
                onItemClick = { onAnggotaClick(it.id)}
            )
        }
    }
}

@Composable
fun ListAnggota(
    itemAnggota: List<Anggota>,
    modifier: Modifier = Modifier,
    onItemClick: (Anggota) -> Unit = {}
){
    LazyColumn(modifier = Modifier){
        items(
            items = itemAnggota, key = {it.id}){
                person ->
            DataAnggota(
                anggota = person,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onItemClick(person) }
            )
        }
    }
}

@Composable
fun DataAnggota(
    anggota: Anggota,
    modifier: Modifier = Modifier
){
    Card (
        modifier = Modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ){
        Column (
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ){
            Row (
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = anggota.nama,
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = anggota.divisi,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = null,
                )
                Text(
                    text = anggota.telpon,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}