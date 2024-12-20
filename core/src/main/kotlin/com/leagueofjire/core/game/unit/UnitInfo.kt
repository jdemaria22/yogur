package com.leagueofjire.core.game.unit

import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import it.unimi.dsi.fastutil.objects.Object2ObjectMap
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap

data class UnitInfo(
	val name: String, val healthBarHeight: Float,
	val baseMoveSpeed: Float,
	val attackRange: Float,
	val attackSpeed: Float,
	val attackSpeedRatio: Float,
	val acquisitionRange: Float,
	val selectionRadius: Float,
	val pathingRadius: Float,
	val gameplayRadius: Float,
	val basicAtkMissileSpeed: Float,
	val basicAtkWindup: Float,
	val tags: Array<String>
) {
	val tagsEnum = tags.map {
		try {
			UnitTag.valueOf(it)
		} catch (iae: IllegalArgumentException) {
			UnitTag.Unit_
		}
	}.distinct()
	val isChampion = tagsEnum.contains(UnitTag.Unit_Champion)
	val isJungle = tagsEnum.contains(UnitTag.Unit_Monster_Camp)
	val isImportantJungle =
		tagsEnum.contains(UnitTag.Unit_Monster_Large) || tagsEnum.contains(UnitTag.Unit_Monster_Epic)
	val isMinion = tagsEnum.contains(UnitTag.Unit_Minion)
	val isTurret = tagsEnum.contains(UnitTag.Unit_Structure_Turret)
	val isWard = tagsEnum.contains(UnitTag.Unit_Ward)

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false
		
		other as UnitInfo
		
		if (name != other.name) return false
		if (healthBarHeight != other.healthBarHeight) return false
		if (baseMoveSpeed != other.baseMoveSpeed) return false
		if (attackRange != other.attackRange) return false
		if (attackSpeed != other.attackSpeed) return false
		if (attackSpeedRatio != other.attackSpeedRatio) return false
		if (acquisitionRange != other.acquisitionRange) return false
		if (selectionRadius != other.selectionRadius) return false
		if (pathingRadius != other.pathingRadius) return false
		if (gameplayRadius != other.gameplayRadius) return false
		if (basicAtkMissileSpeed != other.basicAtkMissileSpeed) return false
		if (basicAtkWindup != other.basicAtkWindup) return false
		if (!tags.contentEquals(other.tags)) return false
		
		return true
	}
	
	override fun hashCode(): Int {
		var result = name.hashCode()
		result = 31 * result + healthBarHeight.hashCode()
		result = 31 * result + baseMoveSpeed.hashCode()
		result = 31 * result + attackRange.hashCode()
		result = 31 * result + attackSpeed.hashCode()
		result = 31 * result + attackSpeedRatio.hashCode()
		result = 31 * result + acquisitionRange.hashCode()
		result = 31 * result + selectionRadius.hashCode()
		result = 31 * result + pathingRadius.hashCode()
		result = 31 * result + gameplayRadius.hashCode()
		result = 31 * result + basicAtkMissileSpeed.hashCode()
		result = 31 * result + basicAtkWindup.hashCode()
		result = 31 * result + tags.contentHashCode()
		return result
	}

	val loadIcon by lazy(LazyThreadSafetyMode.NONE) {
		if (!isChampion) return@lazy null
		val resource = UnitInfo::class.java.getResourceAsStream("champion/icons_champs/${name.lowercase()}_square.png")
			?: return@lazy null
		Texture(Pixmap(Gdx2DPixmap(resource, Gdx2DPixmap.GDX2D_FORMAT_RGBA4444)))
	}

	companion object {
		lateinit var objectMapper: ObjectMapper
		
		lateinit var nameToInfo: Object2ObjectMap<String, UnitInfo>
		
		fun load(file: String = "units.json") {
			objectMapper = ObjectMapper().registerKotlinModule()
			val infos: List<UnitInfo> = objectMapper.readValue(
				UnitInfo::class.java.getResource(file),
				objectMapper.typeFactory.constructCollectionType(List::class.java, UnitInfo::class.java)
			)
			nameToInfo = Object2ObjectOpenHashMap(infos.size)
			infos.forEach { nameToInfo[it.name.lowercase()] = it }
		}
		
		val unknownInfo = UnitInfo(
			"", 0F, 0F, 0F, 0F,
			0F, 0F, 0F, 0F, 0F,
			0F, 0F, emptyArray()
		)
		
	}
	
}