package com.leagueofjire.scripts.utils

import com.leagueofjire.ScreenPosition
import com.leagueofjire.core.game.unit.IGameUnit
import com.leagueofjire.core.game.unit.champion.spell.SpellInfo
import com.leagueofjire.game.GameRenderer
import com.leagueofjire.game.unit.GameUnit
import it.unimi.dsi.fastutil.objects.ObjectList
import java.util.Optional

interface Targeting {

    fun getBestTarget(champions: ObjectList<IGameUnit>, localPlayer: IGameUnit): Optional<GameUnit>

    fun getBestTargetForRange(champions: ObjectList<IGameUnit>, localPlayer: IGameUnit, range: Float): Optional<IGameUnit>

    fun getBestMonster(monsters: ObjectList<IGameUnit>, localPlayer: IGameUnit): Optional<GameUnit>

    fun getBestMinion(minions: ObjectList<IGameUnit>, localPlayer: IGameUnit): Optional<GameUnit>

    fun getLastHitMinion(minions: ObjectList<IGameUnit>, localPlayer: IGameUnit): Optional<GameUnit>

    fun castPointForCollision(target: Optional<IGameUnit>, spell: SpellInfo, localPlayer: IGameUnit, renderer: GameRenderer, time: Float, targetSpell: Boolean) : Optional<ScreenPosition>

    fun getBestTargetLaneClear(turrets: ObjectList<IGameUnit>, monsters: ObjectList<IGameUnit>,minions: ObjectList<IGameUnit>, champions: ObjectList<IGameUnit>, localPlayer: IGameUnit): Optional<GameUnit>

    fun castPointForCircle(target: Optional<IGameUnit>, spell: SpellInfo, localPlayer: IGameUnit, renderer: GameRenderer) : Optional<ScreenPosition>

    fun getBestTargetForRangeArea(champions: ObjectList<IGameUnit>, localPlayer: IGameUnit, range: Float): Optional<IGameUnit>
}