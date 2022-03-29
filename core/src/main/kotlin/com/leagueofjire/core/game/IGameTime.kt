package com.leagueofjire.core.game

import com.leagueofjire.core.offsets.Offsets
import com.leagueofjire.game.GameTime
import org.jire.kna.attach.AttachedModule
import org.jire.kna.attach.AttachedProcess
import org.jire.kna.float
import org.jire.kna.int

object IGameTime : GameTime {
	
	override var seconds = -1F
	override var gamePing = -1

	fun update(process: AttachedProcess, baseModule: AttachedModule): Boolean {
		seconds = process.float(baseModule.address + Offsets.GameTime)
		val a = process.int(baseModule.address + Offsets.TestGamePingA)
		val b = process.int(a.toLong() + Offsets.TestGamePingB)
		gamePing = process.int(b.toLong() + Offsets.TestGamePingC)
		return true
	}
	
	override fun update(): Boolean {
		TODO("Not yet implemented")
	}
}