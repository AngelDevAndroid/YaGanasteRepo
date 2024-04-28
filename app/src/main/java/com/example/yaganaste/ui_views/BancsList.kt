package com.example.yaganaste.ui_views

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.example.yaganaste.data.BancsModel
import com.example.yaganaste.data.EntyBancsModel
import com.example.yaganaste.navigation.AppScreens
import kotlinx.coroutines.job

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BancsList(nhc: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Bancos",
                    modifier = Modifier,
                     color = Color.Black)
            })
        },
        content = {
                padding ->
                BancListContent(
                padding = padding,
                nhc = nhc
            )
        }
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BancListContent(
    gamesViewModel: BancsViewModel = hiltViewModel(),
    padding: PaddingValues,
    nhc: NavController) {

    val shwPb by gamesViewModel.shwPb.collectAsState(false)
    val listGames by gamesViewModel.listGamesVm.collectAsState()

    val listGamesX: MutableList<BancsModel> = mutableListOf()

    val msgApi by gamesViewModel.msgApiVm.collectAsState("")

    var banc by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var objBanc by remember {
        mutableStateOf(BancsModel())
    }
    val focusRequester = FocusRequester()
    val keyboardController = LocalSoftwareKeyboardController.current

    //LoadingPb(state = shwPb, msgApi = msgApi, false, onDismiss = {})

    /*LaunchedEffect(banc) {
        coroutineContext.job.invokeOnCompletion {
            focusRequester.requestFocus()
        }
    }*/
    Spacer(modifier = Modifier.height(0.dp))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        TextField(
            value = banc,
            onValueChange = {
                banc = it
            },
            placeholder = {
                Text(text = "Banco...",
                    color = Color.Gray)
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {

                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        ListBancs(search = banc, listGames = listGames, padding = padding, nhc)
    }
}

@Composable
fun ListBancs(search: TextFieldValue, listGames: MutableList<BancsModel>,
              padding: PaddingValues, nhc: NavController) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        items(items = listGames.filter {
            it.bankName?.contains(search.text)!!
        }) {list ->
            ItemCardBancs(games = list, nhc = nhc)
        }
    }

    Log.d("LIST_GAMES", listGames.toString())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemCardBancs(games: BancsModel,
                  nhc: NavController) {

    val context = LocalContext.current

    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(
                8.dp,
                top = 4.dp,
                bottom = 4.dp,
                end = 8.dp
            )
            .fillMaxWidth(),
        onClick = {
            nhc.navigate(AppScreens.BancsDetail.route + "/${games.bankName}" + "/${games.description}")
        }) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                painter = rememberAsyncImagePainter(
                    model = games.url,
                    imageLoader = ImageLoader
                        .Builder(context)
                        .crossfade(true)
                        .build()),
                contentDescription = "images",
                contentScale = ContentScale.Crop
            )

            Row(modifier = Modifier.padding(top = 5.dp)) {

                Text(text = games.bankName.toString(),
                     modifier = Modifier,
                     fontWeight = FontWeight.Bold,
                     textAlign = TextAlign.Start)

                FavoriteButton(modifier = Modifier.padding(8.dp), objBanc = games)
            }
        }
    }
}

@Composable
fun FavoriteButton(
    vmBancs: FavoriteBancViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    objBanc: BancsModel,
    color: Color = Color(0xFF7E7B7C)
) {

    var isFavorite by remember { mutableStateOf(false) }
    vmBancs.getLisFavorite()
    val listBancs by vmBancs
        .vmList.collectAsState()

    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            isFavorite = !isFavorite
            val nBanc = EntyBancsModel(0, objBanc.bankName, objBanc.description, objBanc.age, objBanc.url)
            vmBancs.addFavorite(nBanc)
            Log.d("RESPONSE_API", " FAVORITE $listBancs")
        }
    ) {
        Icon(
            tint = color,
            modifier = modifier.graphicsLayer {
                scaleX = 1.3f
                scaleY = 1.3f
            },
            imageVector = if (isFavorite) {
                Icons.Filled.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = null
        )
    }
}