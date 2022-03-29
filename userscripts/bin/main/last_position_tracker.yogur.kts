import com.leagueofjire.scripts.utils.core.ITargeting
import com.yogur.panel.scripts.AutoSpell
import com.yogur.panel.scripts.ChampionTracker

val onMinimap = true
val onWorld = true
val onWorldColor: Color = Color.WHITE

eachChampion {
	if(!ChampionTracker.isEnabled()) return@eachChampion
	if (!isVisible) {
		val timeMissing = (time.seconds - lastVisibleTime).toInt()

		if (onMinimap) minimap[this].use {
			draw(this, 28F)
		}

		if (onWorld) renderer[this].use {
			draw(this, 50F)
			font.color = onWorldColor
			font.text("$name $timeMissing", this)
		}
	}
}

fun GameChampion.draw(screenPosition: ScreenPosition, size: Float) {
	val sprite = sprite ?: return
	sprite.draw(screenPosition.x.toFloat(), screenPosition.y.toFloat() - size, size, size)
}