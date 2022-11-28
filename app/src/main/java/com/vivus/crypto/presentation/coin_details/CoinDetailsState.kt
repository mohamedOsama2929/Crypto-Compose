package com.vivus.crypto.presentation.coin_details

import com.vivus.crypto.domain.model.Coin
import com.vivus.crypto.domain.model.CoinDetails

data class CoinDetailsState(
    val coin: CoinDetails? = null,
    val error: String = "",
    val isLoading: Boolean = false
)
