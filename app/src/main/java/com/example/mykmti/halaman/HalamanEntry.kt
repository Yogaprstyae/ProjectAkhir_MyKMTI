package com.example.mykmti.halaman

import com.example.mykmti.model.DetailAnggota
import com.example.mykmti.model.EntryViewModel
import com.example.mykmti.model.PenyediaViewModel
import com.example.mykmti.model.UIStateAnggota
import com.example.mykmti.navigasi.AnggotaTopAppBar
import com.example.mykmti.navigasi.DestinasiNavigasi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
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
            onDetailValueChange = viewModel::updateUIState,
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
    onDetailValueChange: (DetailAnggota) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column (
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
    ){
        FormInputAnggota(
            detailAnggota = uiStateAnggota.detailAnggota,
            onValueChange =  onDetailValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            enabled = uiStateAnggota.isEntryValid,
            shape = RoundedCornerShape(50.dp),
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
//    var divisi by rememberSaveable { mutableStateOf(Divisi.PH.name) }
//    var endDate by rememberSaveable { mutableStateOf(Date().time) }
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))

    ){
        OutlinedTextField(
            value = detailAnggota.nama,
            onValueChange = {onValueChange(detailAnggota.copy(nama = it))},
            shape = RoundedCornerShape(50.dp),
            label = { Text("Nama") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person,
                    contentDescription = null)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailAnggota.divisi,
            onValueChange = {onValueChange(detailAnggota.copy(divisi = it))},
            shape = RoundedCornerShape(50.dp),
            label = { Text("Divisi") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.List,
                    contentDescription = null)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
//        DivisiDropdownMenu { divisi = it }
        OutlinedTextField(
            value = detailAnggota.telpon,
            onValueChange = {onValueChange(detailAnggota.copy(telpon = it))},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = RoundedCornerShape(50.dp),
            label = { Text("Telepon")},
            leadingIcon = {
                Icon(imageVector = Icons.Default.Phone, contentDescription = null)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailAnggota.namaKeg,
            onValueChange = {onValueChange(detailAnggota.copy(namaKeg = it))},
            shape = RoundedCornerShape(50.dp),
            label = { Text("Nama Kegiatan")},
            leadingIcon = {
                Icon(imageVector = Icons.Default.Edit, contentDescription = null)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailAnggota.tglKeg,
            onValueChange = {onValueChange(detailAnggota.copy(tglKeg = it))},
            shape = RoundedCornerShape(50.dp),
            label = { Text("Tanggal Kegiatan")},
            leadingIcon = {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = null)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
//        EndDateTextField { endDate = it }
        OutlinedTextField(
            value = detailAnggota.dana,
            onValueChange = {onValueChange(detailAnggota.copy(dana = it))},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = RoundedCornerShape(50.dp),
            label = { Text("Total Dana yang Diminta")},
            leadingIcon = {
                Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        if (enabled){
        }
        Spacer(modifier = Modifier.padding(top = 2.dp))
        Divider(
            thickness = dimensionResource(R.dimen.padding_small),
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
        )
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DivisiDropdownMenu(divisi: (String) -> Unit) {
//    Column(
//        verticalArrangement = Arrangement.spacedBy(8.dp)
//    ) {
//        val options = getDivisiList().map { it.name }
//        var expanded by remember { mutableStateOf(false) }
//        var selectedOptionText by remember { mutableStateOf(options[0]) }
//        ExposedDropdownMenuBox(
//            expanded = expanded,
//            onExpandedChange = { expanded = !expanded },
//        ) {
//            OutlinedTextField(
//                modifier = Modifier
//                    .menuAnchor()
//                    .fillMaxSize(),
//                readOnly = true,
//                value = selectedOptionText,
//                shape = RoundedCornerShape(50.dp),
//                label = { Text("Divisi")},
//                leadingIcon = {
//                    Icon(imageVector = Icons.Default.List,
//                        contentDescription = null)
//                },
//                onValueChange = {},
//                singleLine = true,
//                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
//            )
//            ExposedDropdownMenu(
//                expanded = expanded,
//                onDismissRequest = { expanded = false },
//            ) {
//                options.forEach { selectionOption ->
//                    DropdownMenuItem(
//                        text = { Text(selectionOption) },
//                        onClick = {
//                            selectedOptionText = selectionOption
//                            divisi(selectionOption)
//                            expanded = false
//                        }
//
//                    )
//                }
//            }
//        }
//    }
//}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun EndDateTextField(endDate: (Long) -> Unit) {
//
//    val interactionSource = remember { MutableInteractionSource() }
//    val isPressed: Boolean by interactionSource.collectIsPressedAsState()
//
//    val currentDate = Date().toFormattedString()
//    var selectedDate by rememberSaveable { mutableStateOf(currentDate) }
//
//    val context = LocalContext.current
//
//    val calendar = Calendar.getInstance()
//    val year: Int = calendar.get(Calendar.YEAR)
//    val month: Int = calendar.get(Calendar.MONTH)
//    val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
//    calendar.time = Date()
//
//    val datePickerDialog =
//        DatePickerDialog(context, { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
//            val newDate = Calendar.getInstance()
//            newDate.set(year, month, dayOfMonth)
//            selectedDate = "${month.toMonthName()} $dayOfMonth, $year"
//            endDate(newDate.timeInMillis)
//        }, year, month, day)
//
//    OutlinedTextField(
//        modifier = Modifier.fillMaxWidth(),
//        readOnly = true,
//        value = selectedDate,
//        label = { Text("Tanggal Kegiatan")},
//        onValueChange = {},
//        shape = RoundedCornerShape(50.dp),
//        leadingIcon = {
//            Icon(imageVector = Icons.Default.DateRange, contentDescription = null)
//        },
//        interactionSource = interactionSource
//    )
//
//    if (isPressed) {
//        datePickerDialog.show()
//    }
//}
//
//fun Int.toMonthName(): String {
//    return DateFormatSymbols().months[this]
//}
//
//fun Date.toFormattedString(): String {
//    val simpleDateFormat = SimpleDateFormat("LLLL dd, yyyy", Locale.getDefault())
//    return simpleDateFormat.format(this)
//}

