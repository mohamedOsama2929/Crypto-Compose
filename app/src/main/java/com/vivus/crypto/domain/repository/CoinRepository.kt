package com.vivus.crypto.domain.repository

import com.vivus.crypto.data.remote.dto.CoinDetailsDto
import com.vivus.crypto.data.remote.dto.CoinDto

interface CoinRepository {
    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoinById(coinId: String): CoinDetailsDto
}