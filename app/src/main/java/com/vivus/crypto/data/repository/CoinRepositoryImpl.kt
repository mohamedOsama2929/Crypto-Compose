package com.vivus.crypto.data.repository

import com.vivus.crypto.data.remote.CoinPaprikaApi
import com.vivus.crypto.data.remote.dto.CoinDetailsDto
import com.vivus.crypto.data.remote.dto.CoinDto
import com.vivus.crypto.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val coinPaprikaApi: CoinPaprikaApi
) : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return coinPaprikaApi.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailsDto {
        return coinPaprikaApi.getCoinById(coinId)
    }
}
