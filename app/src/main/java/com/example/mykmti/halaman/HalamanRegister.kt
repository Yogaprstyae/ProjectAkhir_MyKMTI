package com.example.mykmti.halaman

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mykmti.R
import com.example.mykmti.components.CButton
import com.example.mykmti.components.CTextField
import com.example.mykmti.components.CTextFieldE
import com.example.mykmti.components.CTextFieldP
import com.example.mykmti.navigasi.Screen
import com.example.mykmti.ui.theme.AlegreyaFontFamily
import com.example.mykmti.ui.theme.AlegreyaSansFontFamily

@Composable
fun HalamanRegiter(
    navController: NavHostController,
) {
    var nama by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(painter = painterResource(id = R.drawable.bg),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            // Logo
            Image(

                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 54.dp)
                    .height(60.dp)
                    .align(Alignment.Start)
                    .offset(x = (-5).dp)
            )

            Text(
                text = "Sign Up",
                style = TextStyle(
                    fontSize = 28.sp,
                    fontFamily = AlegreyaFontFamily,
                    fontWeight = FontWeight(500),
                    color = Color.Black
                ),
                modifier = Modifier.align(Alignment.Start)
            )

            Text(
                "Sign Up sekarang untuk mengajukan proposal kegiatan.",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = AlegreyaSansFontFamily,
                    color = Color.Black
                ),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 24.dp)
            )


            // Text Field
            CTextField(hint = "Nama Lengkap",
                value = nama,
                onValueChange = {
                    nama = it
                },
            )

            CTextFieldE(hint = "Email Address",
                value = email,
                onValueChange = {
                    email = it
                })

            CTextFieldP(hint = "Password",
                value = password,
                onValueChange = {
                    password = it
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            CButton(
                navController,
                text = "Sign Up")

            Row(
                modifier = Modifier.padding(top=12.dp, bottom = 52.dp)
            ){
                Text("Don't have an account? ",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = AlegreyaSansFontFamily,
                        color = Color.Black
                    )
                )

                Text("Sign In",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = AlegreyaSansFontFamily,
                        fontWeight = FontWeight(800),
                        color = Color.Blue
                    ),
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.Login.route)
                    }
                )
            }
        }
    }
}