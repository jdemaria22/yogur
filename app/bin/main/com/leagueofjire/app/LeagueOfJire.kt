package com.leagueofjire.app

import com.badlogic.gdx.utils.ScreenUtils
import com.leagueofjire.core.game.DefaultGameContext
import com.leagueofjire.core.game.IGameTime
import com.leagueofjire.core.game.IGameRenderer
import com.leagueofjire.core.game.IGameMinimap
import com.leagueofjire.core.game.IGameUnitManager
import com.leagueofjire.core.game.IGameLocalPlayer
import com.leagueofjire.core.game.IGameHoveredUnit
import com.leagueofjire.core.game.IGameChatOpen
import com.leagueofjire.core.LeagueOfLegendsHook
import com.leagueofjire.core.game.unit.champion.spell.SpellInfo
import com.leagueofjire.core.game.unit.UnitInfo
import com.sun.jna.platform.win32.Win32Exception


object LeagueOfJire {
	@JvmStatic
	fun main(args: Array<String>) {
		try {
			runYogur()
		} catch (e: Exception){
			println(e.message)
			return
		}
	}

	private fun runYogur(){
		try {
			val hook = LeagueOfLegendsHook.hook()

			UnitInfo.load()
			SpellInfo.load()

			val gameContext = loadDefaultContext(hook)
			if (!gameContext.update()) return
			val overlay = Overlay(gameContext)
			val scriptManager = ScriptManager(gameContext, overlay)
			overlay.open {
				scriptManager.load()
				renderHook {
					if (gameContext.update()) {
						ScreenUtils.clear(0F, 0F, 0F, 0F)
						scriptManager.render()
					}
				}
			}
		} catch (e: Win32Exception){
			println("Process not found...")
		}
	}

	private fun loadDefaultContext(legendsHook: LeagueOfLegendsHook): DefaultGameContext{
		return DefaultGameContext(
			legendsHook,
			IGameTime,
			IGameRenderer,
			IGameMinimap,
			IGameUnitManager,
			IGameLocalPlayer,
			IGameHoveredUnit,
			IGameChatOpen
		)
	}
}