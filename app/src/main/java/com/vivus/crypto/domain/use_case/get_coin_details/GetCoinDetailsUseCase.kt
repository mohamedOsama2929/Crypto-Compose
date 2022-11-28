package com.vivus.crypto.domain.use_case.get_coin_details

import com.vivus.crypto.common.Resource
import com.vivus.crypto.data.remote.dto.toCoinDetails
import com.vivus.crypto.domain.model.CoinDetails
import com.vivus.crypto.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoinDetailsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    suspend operator fun invoke(coinId: String): Flow<Resource<CoinDetails>> = flow {
        try {
            emit(Resource.Loading())
            val coinDetails = repository.getCoinById(coinId = coinId).toCoinDetails()
            emit(Resource.Success(coinDetails))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "An unexpected error occurred"))
        }
    }
}
