package com.vivus.crypto.domain.use_case.get_coins

import com.vivus.crypto.common.Resource
import com.vivus.crypto.data.remote.dto.toCoin
import com.vivus.crypto.domain.model.Coin
import com.vivus.crypto.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins().map { coinDto ->
                coinDto.toCoin()
            }
            emit(Resource.Success(coins))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "An unexpected error occurred"))
        }
    }
}
