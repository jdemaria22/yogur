package com.leagueofjire.scripts

import com.google.gson.annotations.SerializedName

data class ChampionStats(
    @SerializedName("abilityHaste")
    val abilityHaste: String,
    @SerializedName("abilityPower")
    val abilityPower: String,
    @SerializedName("armor")
    val armor: String,
    @SerializedName("armorPenetrationFlat")
    val armorPenetrationFlat: String,
    @SerializedName("armorPenetrationPercent")
    val armorPenetrationPercent: String,
    @SerializedName("attackDamage")
    val attackDamage: String,
    @SerializedName("attackRange")
    val attackRange: String,
    @SerializedName("attackSpeed")
    val attackSpeed: String,
    @SerializedName("bonusArmorPenetrationPercent")
    val bonusArmorPenetrationPercent: String,
    @SerializedName("bonusMagicPenetrationPercent")
    val bonusMagicPenetrationPercent: String,
    @SerializedName("cooldownReduction")
    val cooldownReduction: String,
    @SerializedName("critChance")
    val critChance: String,
    @SerializedName("critDamage")
    val critDamage: String,
    @SerializedName("currentHealth")
    val currentHealth: String,
    @SerializedName("healthRegenRate")
    val healthRegenRate: String,
    @SerializedName("lifeSteal")
    val lifeSteal: String,
    @SerializedName("magicLethality")
    val magicLethality: String,
    @SerializedName("magicPenetrationFlat")
    val magicPenetrationFlat: String,
    @SerializedName("magicPenetrationPercent")
    val magicPenetrationPercent: String,
    @SerializedName("magicResist")
    val magicResist: String,
    @SerializedName("maxHealth")
    val maxHealth: String,
    @SerializedName("moveSpeed")
    val moveSpeed: String,
    @SerializedName("physicalLethality")
    val physicalLethality: String,
    @SerializedName("resourceMax")
    val resourceMax: String,
    @SerializedName("resourceRegenRate")
    val resourceRegenRate: String,
    @SerializedName("resourceValue")
    val resourceValue: String,
    @SerializedName("spellVamp")
    val spellVamp: String,
    @SerializedName("tenacity")
    val tenacity: String
)