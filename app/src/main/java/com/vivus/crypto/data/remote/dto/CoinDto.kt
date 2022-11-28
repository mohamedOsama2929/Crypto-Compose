package com.vivus.crypto.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.vivus.crypto.domain.model.Coin

data class CoinDto(

	@field:SerializedName("symbol")
	val symbol: String? = null,

	@field:SerializedName("is_active")
	val isActive: Boolean? = null,

	@field:SerializedName("is_new")
	val isNew: Boolean? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("rank")
	val rank: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("type")
	val type: String? = null
)

fun CoinDto.toCoin(): Coin {
	return Coin(
		symbol = symbol ?: "",
		isActive = isActive ?: false,
		isNew = isNew ?: false,
		name = name ?: "",
		rank = rank ?: 0,
		id = id ?: "",
		type = type ?: ""
	)
}

