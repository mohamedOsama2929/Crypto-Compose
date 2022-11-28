package com.vivus.crypto.presentation.coin_list

import com.vivus.crypto.domain.model.Coin

data class CoinListState(
    val coins: List<Coin> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false
)
