package com.leagueofjire.scripts

import com.google.gson.annotations.SerializedName

data class ActivePlayer(
    @SerializedName("championStats")
    val championStats: ChampionStats,
    @SerializedName("currentGold")
    val currentGold: String,
    @SerializedName("level")
    val level: String,
    @SerializedName("summonerName")
    val summonerName: String
)