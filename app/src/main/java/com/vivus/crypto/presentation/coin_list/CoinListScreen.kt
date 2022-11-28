package com.vivus.crypto.presentation.coin_list

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.vivus.crypto.presentation.Screen
import com.vivus.crypto.presentation.coin_list.components.CoinListItem
import com.vivus.crypto.utils.LifeEventListener

@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel(),
    localLifeCycleOwner:LifecycleOwner= LocalLifecycleOwner.current
) {
    val context= LocalContext.current

    LaunchedEffect(key1 = Unit, block = {
        viewModel.getCoins()
    })
  /*  LifeEventListener{
        when(it){
            Lifecycle.Event.ON_RESUME->{
                Toast.makeText(context, "run", Toast.LENGTH_SHORT).show()
            }
            else->{
                Log.d("TAG", "CoinListScreen: ${it.name}")
            }
        }
    }*/
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.coins) { coin ->
                CoinListItem(coin = coin, onItemClick = {
                    navController.navigate(Screen.CoinDetailScreen.route + "/${it.id}")
                })
            }
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                color = androidx.compose.ui.graphics.Color.Red
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

}