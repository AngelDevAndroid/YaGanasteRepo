package com.example.yaganaste.ui_views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter

@Composable
fun BancsDetail(imageBanc: String?,
                bankName: String
) {

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
                .height(250.dp),
            painter = rememberAsyncImagePainter(
                model = imageBanc,
                imageLoader = ImageLoader
                    .Builder(context)
                    .crossfade(true)
                    .build()),
            contentDescription = "images",
            contentScale = ContentScale.Crop
        )

        Text(text = "Nombre: $bankName"?: msg,
            modifier = Modifier.padding(
                top = 10.dp,
                start = 10.dp,
                end = 10.dp))

        Text(text = "Tiempo: age"?: msg,
            modifier = Modifier.padding(
                top = 10.dp,
                start = 10.dp,
                end = 10.dp))

        Text(text = "Description: description"?: msg,
            modifier = Modifier.padding(
                top = 10.dp,
                start = 10.dp,
                end = 10.dp))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailGamesPreview() {
    BancsDetail("", "")
}