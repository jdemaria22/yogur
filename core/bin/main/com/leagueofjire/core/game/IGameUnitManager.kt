package com.leagueofjire.core.game

import com.leagueofjire.core.game.unit.IGameUnit
import com.leagueofjire.core.offsets.Offsets
import com.leagueofjire.core.util.free
import com.leagueofjire.game.GameUnitManager
import com.leagueofjire.game.unit.GameUnit
import it.unimi.dsi.fastutil.ints.Int2ObjectMap
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import it.unimi.dsi.fastutil.longs.LongOpenHashSet
import it.unimi.dsi.fastutil.longs.LongSet
import it.unimi.dsi.fastutil.objects.ObjectArrayList
import it.unimi.dsi.fastutil.objects.ObjectList
import org.jire.kna.Pointer
import org.jire.kna.attach.AttachedModule
import org.jire.kna.attach.AttachedProcess
import org.jire.kna.int

object IGameUnitManager : GameUnitManager {

	private const val MAX_UNITS = 2048

	private var unitsRead = 0
	private val visitedNodes: LongSet = LongOpenHashSet(MAX_UNITS)

	// we have to use `LongArray` because `Array<Pointer>` would result in boxing
	private val unitScanPointers = LongArray(MAX_UNITS) {
		Pointer.alloc(0x30).address
	}

	private fun unitScanPointer(index: Int) = Pointer(unitScanPointers[index])

	val units: Int2ObjectMap<IGameUnit> = Int2ObjectOpenHashMap(MAX_UNITS)

	val unitsIt = Int2ObjectMaps.fastIterable(units)

	val champions: ObjectList<IGameUnit> = ObjectArrayList(10)
	val monsters: ObjectList<IGameUnit> = ObjectArrayList(64)
	val minions: ObjectList<IGameUnit> = ObjectArrayList(6)
	val turrets: ObjectList<IGameUnit> = ObjectArrayList(22)
	private val unitData = Pointer.alloc(0x4000)

	private fun scanUnits(process: AttachedProcess, objectManager: Pointer): Boolean {
		val numMissiles = objectManager.getInt(Offsets.ObjectMapCount)
		if (numMissiles <= 0) return false

		val rootUnitAddress = objectManager.getInt(Offsets.ObjectMapRoot).toLong()
		if (rootUnitAddress <= 0) return false

		unitsRead = 0
		champions.clear()
		monsters.clear()
		visitedNodes.clear()
		minions.clear()
		turrets.clear()
		scanUnit(process, rootUnitAddress)

		return true
	}

	private tailrec fun scanUnit(process: AttachedProcess, address: Long) {
		val unitsRead = unitsRead++
		if (unitsRead >= MAX_UNITS || address <= 0 || visitedNodes.contains(address)) return

		visitedNodes.add(address)

		val data = unitScanPointer(unitsRead)
		if (!process.read(address, data, 0x30)) return
		if (!data.readable()) return

		val networkID = data.getInt(Offsets.ObjectMapNodeNetId)
		if (networkID >= 0x40000000) {
			val unitAddress = data.getInt(Offsets.ObjectMapNodeObject).toLong()
			updateUnit(process, networkID, unitAddress)
		}

		// children
		scanUnit(process, data.getInt(0).toLong())
		scanUnit(process, data.getInt(4).toLong())
		scanUnit(process, data.getInt(8).toLong())
	}

	private fun updateUnit(process: AttachedProcess, networkID: Int, address: Long) {
		if (address <= 0) return

		val unit: IGameUnit
		if (units.containsKey(networkID)) {
			unit = units[networkID]
			if (unit.update(process, unitData, false) && networkID != unit.networkID)
				units[unit.networkID] = unit
		} else {
			unit = IGameUnit().apply { this.address = address }
			if (unit.update(process, unitData, true))
				units[unit.networkID] = unit
		}

		if (unit.isVisible)
			unit.lastVisibleTime = IGameTime.seconds

		if (unit.networkID > 0) {
			val info = unit.info
			if (info.isChampion) {
				if (!champions.contains(unit))
					champions.add(unit)
				// add to champions list
			} else if (info.isMinion) {
				if (!minions.contains(unit))
					minions.add(unit)
			} else if (info.isJungle) {
				if (!monsters.contains(unit))
					monsters.add(unit)
			} else if (info.isTurret) {
				turrets.add(unit)
			}
		}
	}

	fun update(process: AttachedProcess, base: AttachedModule): Boolean {
		val objectManagerOffset = process.int(base.address + Offsets.ObjectManager).toLong()
		if (objectManagerOffset <= 0) return false

		val objectManager = Pointer.alloc(256)
		if (!process.read(objectManagerOffset, objectManager, 256)) return false
		if (!objectManager.readable()) return false

		try {
			if (!scanUnits(process, objectManager)) return false
		} finally {
			objectManager.free(256)
		}

		return true
	}

	@Suppress("UNCHECKED_CAST")
	override fun <T : GameUnit> get(networkID: Int): T = units[networkID] as T

	override fun update(): Boolean {
		TODO("Not yet implemented")
	}

}