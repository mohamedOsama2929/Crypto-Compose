package com.vivus.crypto.presentation.coin_details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowRow
import com.vivus.crypto.presentation.Screen
import com.vivus.crypto.presentation.coin_details.components.CoinTag
import com.vivus.crypto.presentation.coin_details.components.TeamListItem
import com.vivus.crypto.presentation.coin_list.components.CoinListItem

@Composable
fun CoinDetailsScreen(
    viewModel: CoinDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        state.coin?.let { coin ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${coin.rank}. ${coin.name} (${coin.symbol})",
                            style = MaterialTheme.typography.h4,
                            modifier = Modifier
                                .weight(8f)
                        )
                        Text(
                            text = if (coin.isActive) "active" else "inactive",
                            modifier = Modifier
                                .weight(2f)
                                .align(Alignment.CenterVertically),
                            color = if (coin.isActive) androidx.compose.ui.graphics.Color.Green else androidx.compose.ui.graphics.Color.Red,
                            textAlign = TextAlign.End
                        )

                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = coin.description,
                        style = MaterialTheme.typography.body2
                    )
                    Text(
                        text = "Tags",
                        style = MaterialTheme.typography.h4,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    FlowRow(
                        mainAxisSpacing = 10.dp,
                        crossAxisSpacing = 10.dp, modifier = Modifier.fillMaxWidth()
                    ) {
                        coin.tags.forEach { tag ->
                            CoinTag(tag = tag)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Team Members",
                        style = MaterialTheme.typography.h4,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
                items(coin.team) { teamMember ->
                        teamMember?.let {
                            TeamListItem(
                                teamMember = it,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                            )
                        }
                        Divider()

                }

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