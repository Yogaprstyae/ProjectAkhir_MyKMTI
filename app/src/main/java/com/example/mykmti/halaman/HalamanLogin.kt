package com.example.mykmti.halaman

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mykmti.R
import com.example.mykmti.components.CButton
import com.example.mykmti.components.CTextField
import com.example.mykmti.components.DontHaveAccountRow
import com.example.mykmti.navigasi.Screen
import com.example.mykmti.ui.theme.AlegreyaFontFamily
import com.example.mykmti.ui.theme.AlegreyaSansFontFamily

@Composable
fun HalamanLogin(
    navController: NavHostController,
) {
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
                text = "Sign In",
                style = TextStyle(
                    fontSize = 28.sp,
                    fontFamily = AlegreyaFontFamily,
                    fontWeight = FontWeight(500),
                    color = Color.Black
                ),
                modifier = Modifier.align(Alignment.Start)
            )

            Text(
                "Sign In sekarang untuk mengajukan proposal kegiatan.",
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
            CTextField(hint = "Email Address", value = "")

            CTextField(hint = "Password", value = "")

            Spacer(modifier = Modifier.height(24.dp))

            CButton(
                navController,
                text = "Sign In")

            DontHaveAccountRow(
                navController
            )

        }

    }
}


@Preview(showBackground = true, widthDp = 320, heightDp = 640)
@Composable
fun LoginScreenPreview() {
    HalamanLogin(rememberNavController())
}