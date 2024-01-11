package com.example.mykmti

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mykmti.halaman.DestinasiHome
import com.example.mykmti.navigasi.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen (navController: NavHostController){
    var starAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (starAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 300
        )
    )
    LaunchedEffect(key1 = true) {
        starAnimation = true
        delay(1000)
        navController.navigate(Screen.Awal.route)
    }
    Splash(alpha = alphaAnim.value)
}

@Composable
fun Splash(
    alpha: Float
){
    Column(
        modifier = Modifier.fillMaxSize(),
        Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(250.dp)
                .alpha(alpha = alpha),
            painter = painterResource(id = R.drawable.logo)
            , contentDescription = null)
    }
}