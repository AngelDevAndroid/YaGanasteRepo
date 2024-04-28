package com.example.yaganaste.ui_views

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.example.yaganaste.data.EntyBancsModel

@Composable
fun BancsDetail(nameBanc: String?,
                ageBanc: String,
                descBanc: String,
                bancsViewModel: FavoriteBancViewModel = hiltViewModel()
) {

    bancsViewModel.getLisFavorite()
    val listBancs by bancsViewModel.vmList.collectAsState()

    Log.d("DATA_LOCAL", " FAVORITE -> $listBancs")

    val context = LocalContext.current
    val msg = "Detalle no disponible"

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Text(text = "Detalle del banco",
            modifier = Modifier.padding(top = 25.dp,
                bottom = 25.dp),
            fontSize = 25.sp)

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            painter = rememberAsyncImagePainter(
                model = "https://images.vexels.com/media/users/3/263264/isolated/preview/11a26a1102ca38d370338cf6a91459c4-icono-de-banco-de-negocio-de-dinero.png",
                imageLoader = ImageLoader
                    .Builder(context)
                    .crossfade(true)
                    .build()),
            contentDescription = "images",
            contentScale = ContentScale.Crop
        )

        Text(text = "Nombre: $nameBanc"?: msg,
            modifier = Modifier.padding(
                top = 10.dp,
                start = 10.dp,
                end = 10.dp))

        Text(text = "Tiempo: $ageBanc"?: msg,
            modifier = Modifier.padding(
                top = 10.dp,
                start = 10.dp,
                end = 10.dp))

        Text(text = "Description: $descBanc"?: msg,
            modifier = Modifier.padding(
                top = 10.dp,
                start = 10.dp,
                end = 10.dp))

        Button(
            onClick = {
                val nBanc = EntyBancsModel(0, nameBanc, descBanc, ageBanc.toInt(), "")
                bancsViewModel.addFavorite(nBanc)
                Toast.makeText(context, "Agregado a favoritos!", Toast.LENGTH_SHORT).show()
            }
        ) {
            Text("Agregar a favoritos")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailGamesPreview() {
    BancsDetail("", "", "")
}