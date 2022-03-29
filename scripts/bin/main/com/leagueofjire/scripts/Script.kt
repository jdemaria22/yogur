package com.leagueofjire.scripts

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.leagueofjire.ScreenPosition
import com.leagueofjire.core.game.IGameContext
import com.leagueofjire.core.game.unit.champion.spell.SpellInfo
import com.leagueofjire.game.GameContext
import com.leagueofjire.game.unit.GameMinion
import com.leagueofjire.game.unit.GameUnit
import com.leagueofjire.game.unit.champion.GameChampion
import com.leagueofjire.input.KeyboardContext
import com.leagueofjire.input.MouseContext
import com.leagueofjire.overlay.OverlayContext
import com.yogur.panel.UiFrame
import it.unimi.dsi.fastutil.objects.ObjectArrayList
import it.unimi.dsi.fastutil.objects.ObjectList
import java.util.Optional

import kotlin.script.experimental.annotations.KotlinScript

@KotlinScript(
	"Yogur Script",
	fileExtension = "yogur.kts",
	compilationConfiguration = MyScriptCompilationConfiguration::class,
)
abstract class Script(
	val gameContext: IGameContext,
	private val overlayContext: OverlayContext,
	val mouse: MouseContext,
	val keyboard: KeyboardContext
) : GameContext by gameContext,
	OverlayContext by overlayContext {
	private val doRenders: ObjectList<OverlayContext.() -> Unit> = ObjectArrayList()

	fun render(doRender: OverlayContext.() -> Unit) {
		doRenders.add(doRender)
	}

	fun simpleContext() {
		UiFrame.initFrame()
	}

	fun hideContext() {
		UiFrame.hideJFrame()
	}

	fun showContext() {
		UiFrame.showJFrame()
	}

	var orbwalking = false
	fun isOrbwalking(): Boolean {
		return orbwalking
	}

	fun setbwalking(boolean: Boolean) {
		orbwalking = boolean
	}

	fun render() {
		for (i in 0..doRenders.lastIndex)
			doRenders[i](overlayContext)

		for (i in 0..gameContext.unitManager.champions.lastIndex) {
			val champion = gameContext.unitManager.champions[i] ?: continue
			for (ci in 0..eachChampions.lastIndex)
				eachChampions[ci](champion)
		}
		for (i in 0..gameContext.unitManager.minions.lastIndex) {
			val minion = gameContext.unitManager.minions[i] ?: continue
			for (mi in 0..eachMinions.lastIndex)
				eachMinions[mi](minion)
		}
		for (entry in gameContext.unitManager.unitsIt) {
			val unit = entry.value
			for (i in 0..eachUnits.lastIndex)
				eachUnits[i](unit)
		}
	}

	private val eachMinions: ObjectList<GameMinion.() -> Unit> = ObjectArrayList()
	fun eachMinions(eachMinion: GameMinion.() -> Unit){
		eachMinions.add(eachMinion)
	}

	private val eachUnits: ObjectList<GameUnit.() -> Unit> = ObjectArrayList()
	fun eachUnit(eachUnit: GameUnit.() -> Unit) {
		eachUnits.add(eachUnit)
	}

	private val eachChampions: ObjectList<GameChampion.() -> Unit> = ObjectArrayList()
	fun eachChampion(eachChampion: GameChampion.() -> Unit) {
		eachChampions.add(eachChampion)
	}

	inline fun ScreenPosition.use(crossinline ifOnScreen: ScreenPosition.() -> Unit) {
		if (gameContext.renderer.onScreen(this))
			ifOnScreen()
	}

	fun Texture.draw(x: Float, y: Float, width: Float, height: Float) = sprites.drawSprite(this, x, y, width, height)
	fun SpriteBatch.drawSprite(
		texture: Texture,
		x: Float, y: Float,
		width: Float = texture.width.toFloat(), height: Float = texture.height.toFloat()
	) = draw(texture, x, y, width, height, 0, 0, texture.width, texture.height, false, true)

	fun SpriteBatch.setDarkness(percent: Float) = setColor(percent, percent, percent, 1F)

	fun BitmapFont.text(text: String, x: Float, y: Float, batch: SpriteBatch = sprites) =
		draw(batch, text, x, y)

	fun BitmapFont.text(text: String, x: Int, y: Int, batch: SpriteBatch = sprites) =
		draw(batch, text, x.toFloat(), y.toFloat())

	fun BitmapFont.text(text: String, screenPosition: ScreenPosition, batch: SpriteBatch = sprites) =
		text(text, screenPosition.x, screenPosition.y, batch)

	fun getInfoSpell(slot: Int): Optional <SpellInfo> {
		for(spell in gameContext.me.localPlayer.spells){
			if (spell.slot == slot){
				return Optional.of(spell.info)
			}
		}
		return Optional.empty()
	}

	fun getLocalPlayer(): GameUnit {
		return gameContext.me.localPlayer
	}
}