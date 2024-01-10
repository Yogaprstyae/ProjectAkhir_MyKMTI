package com.example.mykmti.navigasi

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mykmti.R
import com.example.mykmti.halaman.DestinasiEntry
import com.example.mykmti.halaman.DestinasiHome
import com.example.mykmti.halaman.DetailsDestination
import com.example.mykmti.halaman.DetailsScreen
import com.example.mykmti.halaman.EntryAnggotaScreen
import com.example.mykmti.halaman.HomeScreen
import com.example.mykmti.halaman.ItemEditDestination
import com.example.mykmti.halaman.ItemEditScreen

@Composable
fun KMTIApp(
    navController: NavHostController = rememberNavController()){
    Navigasi(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnggotaTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
){
    CenterAlignedTopAppBar(title = { Text(title)},
        modifier = Modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack){
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.btn_back) )

                }
            }
        }
    )
}

@Composable
fun Navigasi(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ) {
        composable(DestinasiHome.route
        ) {
            HomeScreen(navigateToItemEntry = {navController.navigate(DestinasiEntry.route)})
        }
        composable(DestinasiEntry.route) {
            EntryAnggotaScreen(navigateBack = { navController.popBackStack() })
        }

        composable(
            DetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(DetailsDestination.anggotaIdArg) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt(DetailsDestination.anggotaIdArg)
            itemId?.let {
                DetailsScreen(navigasiToEditItem = {navController.navigate("${ItemEditDestination.route}/$it")},
                    navigateBack = { navController.popBackStack() })
            }
        }

        composable(
            ItemEditDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemEditDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            ItemEditScreen(navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }
    }
}