package com.leagueofjire.game.unit.champion

import com.badlogic.gdx.graphics.Texture
import com.leagueofjire.game.unit.GameUnit
import com.leagueofjire.game.unit.champion.ai.GameChampionAiManager
import com.leagueofjire.game.unit.champion.buff.GameChampionBuff
import com.leagueofjire.game.unit.champion.spell.GameChampionSpell
import com.leagueofjire.game.unit.champion.spell.GameChampionSpells

interface GameChampion : GameUnit {
	val aiManager: GameChampionAiManager
	val spells: Array<out GameChampionSpell>
	val buffs: ArrayList<GameChampionBuff>
	fun spell(spell: GameChampionSpells): GameChampionSpell?
	val sprite: Texture?
}