package com.leagueofjire.core.game.unit

import com.leagueofjire.core.game.RiotStrings
import com.leagueofjire.core.game.unit.champion.ai.IGameChampionAiManager
import com.leagueofjire.core.game.unit.champion.buff.IGameChampionBuff
import com.leagueofjire.core.game.unit.champion.item.ItemSlot
import com.leagueofjire.core.game.unit.champion.spell.IGameChampionSpell
import com.leagueofjire.core.offsets.Offsets
import com.leagueofjire.core.util.free
import com.leagueofjire.game.unit.GameMinion
import com.leagueofjire.game.unit.GameUnit
import com.leagueofjire.game.unit.champion.GameChampion
import com.leagueofjire.game.unit.champion.buff.GameChampionBuff
import com.leagueofjire.game.unit.champion.spell.GameChampionSpell
import com.leagueofjire.game.unit.champion.spell.GameChampionSpells
import org.jire.kna.Pointer
import org.jire.kna.attach.AttachedProcess
import org.jire.kna.int
import kotlin.math.abs
import kotlin.math.pow


open class IGameUnit : GameUnit, GameChampion, GameMinion {

	override fun spell(spell: GameChampionSpells): GameChampionSpell? {
		for (iSpell in spells)
			if (iSpell.type.api == spell)
				return iSpell
		return null
	}

	override var address = -1L

	var disableUpdating = false

	override val position = IGamePosition()
	override var name = ""

	override var lastVisibleTime = -1F

	override var team = -1
	override var health = -1F
	override var maxHealth = -1F
	override var baseAttack = -1F
	override var bonusAttack = -1F
	override var armor = -1F
	override var bonusArmor = -1F
	override var magicResist = -1F
	override var duration = -1F
	override var isVisible = false
	override var objectIndex = -1
	override var crit = -1F
	override var critMulti = -1F
	override var abilityPower = -1F
	override var attackSpeedMulti = -1F
	override var movementSpeed = -1F
	override var networkID = -1
	override var level = -1F
	override var spawnCount = -1
	override var isAlive = false
	override var isTargetable = false
	override var sizeMultiplier = -1F
	override var attackRange = -1F
	override val isChampion get() = info.isChampion
	override val isImportantJungle get() = info.isImportantJungle
	override val gameplayRadius get() = info.gameplayRadius
	override val sprite get() = info.loadIcon
	override val direction = IGamePosition()
	var info: UnitInfo = UnitInfo.unknownInfo

	fun update(process: AttachedProcess, data: Pointer, deep: Boolean = false): Boolean {
		if (disableUpdating) return false
		if (!process.read(address, data, DATA_SIZE)) return false
		if (!data.readable()) return false

		data.run {
			team = getShort(Offsets.ObjTeam).toInt()
			position.x = getFloat(Offsets.ObjPos)
			position.y = getFloat(Offsets.ObjPos + 4)
			position.z = getFloat(Offsets.ObjPos + 8)
			direction.x = getFloat(Offsets.ObjDirection)
			direction.y = getFloat(Offsets.ObjDirection + 4)
			direction.z = getFloat(Offsets.ObjDirection + 8)
			health = getFloat(Offsets.ObjHealth)
			maxHealth = getFloat(Offsets.ObjMaxHealth)
			baseAttack = getFloat(Offsets.ObjBaseAtk)
			bonusAttack = getFloat(Offsets.ObjBonusAtk)
			armor = getFloat(Offsets.ObjArmor)
			bonusArmor = getFloat(Offsets.ObjBonusArmor)
			magicResist = getFloat(Offsets.ObjMagicRes)
			duration = getFloat(Offsets.ObjExpiry)
			isVisible = getBoolean(Offsets.ObjVisibility)
			objectIndex = getShort(Offsets.ObjIndex).toInt()
			crit = getFloat(Offsets.ObjCrit)
			critMulti = getFloat(Offsets.ObjCritMulti)
			abilityPower = getFloat(Offsets.ObjAbilityPower)
			attackSpeedMulti = getFloat(Offsets.ObjAtkSpeedMulti)
			movementSpeed = getFloat(Offsets.ObjMoveSpeed)
			networkID = getInt(Offsets.ObjNetworkID)
			attackRange = getFloat(Offsets.ObjAtkRange)
			level = getFloat(Offsets.ObjLvl)
			isTargetable = getBoolean(Offsets.ObjTargetable)
			sizeMultiplier = getFloat(Offsets.ObjSizeMultiplier)
			spawnCount = getInt(Offsets.ObjSpawnCount)
			isAlive = spawnCount % 2 == 0
		}

		if (deep) deepUpdate(process, data)

		if (info.isChampion) {
			updateChampion(process, data, deep)
		} else if (info == UnitInfo.unknownInfo) {
			// try reading missile extension
		}

		return true
	}

	private fun deepUpdate(process: AttachedProcess, data: Pointer): Boolean {
		val nameAddress = data.getInt(Offsets.ObjName).toLong()
		if (nameAddress <= 0) return false

		name = RiotStrings().riotString(process, nameAddress)
		if (name.isNotEmpty()) {
			info = UnitInfo.nameToInfo[name] ?: UnitInfo.unknownInfo
			when (name) {
				"testcube", "testcuberender", "testcuberender10vision",
				"s5test_wardcorpse",
				"sru_camprespawnmarker", "sru_plantrespawnmarker",
				"preseason_turret_shield" -> disableUpdating = true
			}
		}

		return true
	}

	override var spells: Array<IGameChampionSpell> = defaultSpells
	override var buffs: ArrayList<GameChampionBuff> = arrayListOf()
	override var aiManager: IGameChampionAiManager = IGameChampionAiManager()
	var itemSlots: Array<ItemSlot> = defaultItemSlots

	private fun updateChampion(process: AttachedProcess, data: Pointer, deep: Boolean = false) =
		updateSpells(process, data, deep) && updateBuff(process, data) && updateAiManager(process, data)

	companion object {
		private const val DATA_SIZE = 0x4000L
		private const val BUFF_SIZE = 0x78L
		private const val ADDRESS_BUFF_ITERATOR = 0x8
		private val defaultSpells = emptyArray<IGameChampionSpell>()
		private val defaultItemSlots = emptyArray<ItemSlot>()
		private const val AI_MANAGER_BASE : Int = 4
		private const val AI_VALUE_TO_MOVE : Int = 8
	}
	private fun updateAiManager(process: AttachedProcess, data: Pointer): Boolean{
		try {
			val offsetSubBase : Long = Offsets.AiManager - AI_MANAGER_BASE
			var objectAiManagerMoveTwoBytes : Int = data.getInt((offsetSubBase + AI_VALUE_TO_MOVE) + (data.getByte((offsetSubBase + AI_MANAGER_BASE))) * AI_MANAGER_BASE)
			val objectAiManagerSubBase : Int = data.getInt(offsetSubBase)
			objectAiManagerMoveTwoBytes = objectAiManagerMoveTwoBytes xor objectAiManagerSubBase.inv()
			val base : Int = process.int(objectAiManagerMoveTwoBytes.toLong() + AI_VALUE_TO_MOVE)
			if (aiManager.load(process, base)) return true
			return false
		} catch (ex : Exception){
			println("Error in IGameUnit.updateAiManager: " + ex.message)
			return false
		}
	}

	private fun updateSpells(process: AttachedProcess, data: Pointer, deep: Boolean): Boolean {
		if (spells === defaultSpells) spells = Array(6) { IGameChampionSpell(it) }
		for (spell in spells) {
			val address = data.getInt(Offsets.ObjSpellBook + (spell.slot * 4)).toLong()
			if (address <= 0) return false
			if (!spell.load(process, address, deep)) return false
		}
		return true
	}

	private fun updateBuff(process: AttachedProcess, data: Pointer): Boolean {
		try {
			buffs.clear()
			val buffArrayBgn = data.getInt( Offsets.ObjBuffManager + Offsets.BuffManagerEntriesArray)
			val buffArrayEnd = data.getInt(Offsets.ObjBuffManager + Offsets.BuffManagerEndArray)
			var currentAddress = buffArrayBgn
			while (currentAddress != buffArrayEnd){
				val buffPointer = process.int(currentAddress.toLong()).toLong()

				val buffPointerAlloc = Pointer.alloc(BUFF_SIZE)
				if (!process.read(buffPointer, buffPointerAlloc, BUFF_SIZE)) {
					currentAddress += ADDRESS_BUFF_ITERATOR
					continue
				}
				if (!buffPointerAlloc.readable()) {
					currentAddress += ADDRESS_BUFF_ITERATOR
					continue
				}

				val dataBuff =  buffPointerAlloc.getInt(Offsets.BuffName)
				if (dataBuff == 0 ) {
					currentAddress += ADDRESS_BUFF_ITERATOR
					continue
				}

				buffs.add(IGameChampionBuff(
					RiotStrings().riotString(process, dataBuff.toLong() + Offsets.BuffName)
					,buffPointerAlloc.getInt(Offsets.BuffEntryBuffCount)
					,buffPointerAlloc.getFloat(Offsets.BuffEntryBuffEndTime)))
				currentAddress += ADDRESS_BUFF_ITERATOR
			}
			if (buffs.size > 0) return true
			return false
		} catch (ex : Exception){
			println("Error in IGameUnit.updateBuff: " + ex.message)
			return true
		}
	}

	private fun updateItems(process: AttachedProcess): Boolean {

		val itemsAddress = process.int(address + Offsets.ObjItemList).toLong()
		if (itemsAddress <= 0) return false

		val itemsData = Pointer.alloc(0x100)
		if (!process.read(itemsAddress, itemsData, 0x100)) return false
		if (!itemsData.readable()) return false

		try {
			if (itemSlots === defaultItemSlots) itemSlots = Array(6) { ItemSlot(it) }
			for (slot in itemSlots) {
				slot.isEmpty = true

				val itemPtr = itemsData.getInt((slot.slot * 0x10) + Offsets.ItemListItem)
				if (itemPtr <= 0) continue

				val itemInfoPtr = process.int(itemPtr + Offsets.ItemInfo).toLong()
				if (itemInfoPtr <= 0) continue

				val id = process.int(itemInfoPtr + Offsets.ItemInfoId)
				slot.isEmpty = false
				// slot.stats = GetItemInfoById(id)
			}

			return true
		} finally {
			itemsData.free(0x100)
		}
	}

	fun distance(otherUnit: IGameUnit) =
		abs(position.x - otherUnit.position.x) +
				abs(position.y - otherUnit.position.y) +
				abs(position.z - otherUnit.position.z)

	fun distanceBetweenTargets(otherUnit1: IGameUnit, otherUnit2: IGameUnit) =
		abs((otherUnit1.position.x - otherUnit2.position.x).pow(2) +
				abs(otherUnit1.position.y - otherUnit2.position.y).pow(2) +
				abs(otherUnit1.position.z - otherUnit2.position.z).pow(2)).pow(0.5F)

	fun withinDistance(otherUnit: IGameUnit, distance: Float) = distance(otherUnit) <= distance
}