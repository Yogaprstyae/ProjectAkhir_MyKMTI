package com.example.mykmti.navigasi

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Awal : Screen("halaman_awal")
    object Login : Screen("halaman_login")

    object Regis : Screen("halaman_regis")


}