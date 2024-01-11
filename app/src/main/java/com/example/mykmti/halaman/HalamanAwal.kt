package com.example.mykmti.halaman

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mykmti.R
import com.example.mykmti.components.CButton
import com.example.mykmti.components.DontHaveAccountRow
import com.example.mykmti.navigasi.DestinasiNavigasi
import com.example.mykmti.navigasi.Screen
import com.example.mykmti.ui.theme.AlegreyaFontFamily
import com.example.mykmti.ui.theme.AlegreyaSansFontFamily

object DestinasiAwal : DestinasiNavigasi {
    override val route = "awal"
    override val titleRes = R.string.halaman_awal
}
@Composable
fun HalamanAwal(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier.fillMaxSize()
    ){
        Image(painter = painterResource(id = R.drawable.bg),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
            )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Image(painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .width(230.dp)
                    .height(240.dp),
                contentScale = ContentScale.Fit
                )
            Text("Selamat Datang",
                fontSize = 32.sp,
                fontFamily = AlegreyaFontFamily,
                fontWeight = FontWeight(700),
                color = Color.Black
                )
            Text("Keterbukaan Mendefinisikan Setiap Kegiatan Kami",
                    textAlign = TextAlign.Center,
                    fontFamily = AlegreyaFontFamily,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(500),
                    color = Color.Black
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(onClick = { navController.navigate(Screen.Login.route)},
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                ) {
                Text(text = "Sign In Melalui Email",
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontFamily = AlegreyaSansFontFamily,
                        fontWeight = FontWeight(500),
                        color = Color.White
                    )
                ) }
            DontHaveAccountRow(
                navController
            )
        }
    }
}


@Preview(showBackground = true, widthDp = 320, heightDp = 640)
@Composable
fun HalamanAwalPreview(){
    HalamanAwal(rememberNavController())
}