package com.vivus.crypto.presentation.coin_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vivus.crypto.common.Constants.PARAM_COIN_ID
import com.vivus.crypto.common.Resource
import com.vivus.crypto.domain.model.CoinDetails
import com.vivus.crypto.domain.use_case.get_coin_details.GetCoinDetailsUseCase
import com.vivus.crypto.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailsViewModel @Inject constructor(
    private val getCoinDetailsUseCase: GetCoinDetailsUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailsState())
    val state: State<CoinDetailsState> = _state

    init {
        savedStateHandle.get<String>(PARAM_COIN_ID)?.let { coinId ->
            getCoinDetails(coinId)
        }
    }

    private fun getCoinDetails(coinId: String) {
        viewModelScope.launch {
            getCoinDetailsUseCase(coinId).collect { result->
                when(result){
                    is Resource.Success -> {
                        _state.value = CoinDetailsState(coin = result.data)
                    }
                    is Resource.Error -> {
                        _state.value = CoinDetailsState(error = result.message ?: "An unexpected error occurred")
                    }
                    is Resource.Loading -> {
                        _state.value = CoinDetailsState(isLoading = true)
                    }
                }
            }
        }
    }
}
