package com.vivus.crypto.domain.model

import com.vivus.crypto.data.remote.dto.Links
import com.vivus.crypto.data.remote.dto.TeamMember

data class CoinDetails(
    val symbol: String,
    val isActive: Boolean,
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val id: String,
    val type: String,
    val description: String,
    val tags: List<String>,
    val team: List<TeamMember?>,
    val links: Links,
    val country: String
)
