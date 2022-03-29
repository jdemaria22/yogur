import com.leagueofjire.scripts.otherunits.WardAwareness
import com.leagueofjire.scripts.otherunits.core.IManageOtherUnits
import com.leagueofjire.scripts.utils.core.IDrawings
import com.leagueofjire.scripts.utils.core.IGeometry
import java.awt.MouseInfo

private val geometry = IGeometry()
private val drawings = IDrawings()
private val iManageOtherUnits = IManageOtherUnits()
private val updateOtherUnits = iManageOtherUnits.init()
private val localPlayer = getLocalPlayer()
val onMinimap = true
val onWorld = true
eachUnit {
    if (!updateOtherUnits) return@eachUnit
    iManageOtherUnits.wards.forEach {
        if (!isAlive) return@forEach
        if (localPlayer.team == team) return@forEach
        if (it.name == name) {
            if (onMinimap) minimap[this].use {
                shapes.color = it.color
                drawings.drawCircleWorldOnMinimap(position, it.radius.toFloat(), 100, minimap, shapes)
            }

            if (onWorld) renderer[this].use {
                font.color = it.color
                font.text(it.name, this.x -50 , this.y)
                shapes.color = it.color
                drawings.drawCircleWorld(position, it.radius.toFloat(), 100, renderer, shapes)
            }
        }
    }

    iManageOtherUnits.clones.forEach {
        if (!isAlive) return@forEach
        if (localPlayer.team == team) return@forEach
        if (it.name == name) {
            font.color = it.color
            font.text(it.name, renderer[this].x -50 , renderer[this].y)
            shapes.color = it.color
            drawings.drawCircleWorld(position, 65F, 100, renderer, shapes)
        }
    }

    iManageOtherUnits.traps.forEach {
        if (!isAlive) return@forEach
        if (localPlayer.team == team) return@forEach
        if (it.name == name) {
            font.color = it.color
            font.text(it.name, renderer[this].x -50 , renderer[this].y)
            shapes.color = it.color
            drawings.drawCircleWorld(position, it.radius.toFloat(), 100, renderer, shapes)
        }
    }
}

fun drawAwareness(listWardAwareness: ArrayList<WardAwareness>){
    var spotDist: Float
    var clickDist: Float
    val localPlayerPos = getLocalPlayer().position
    var clickPositionGamePosition: ScreenPosition

    listWardAwareness.forEach {
        spotDist = geometry.distanceBetweenTargets(localPlayerPos, it.movePosition)
        if (spotDist < 400F && spotDist > 70F){
            shapes.color = Color.YELLOW
            drawings.drawCircleWorld(it.movePosition,15F, 100, renderer, shapes)
        }
        if (spotDist < 70F){
            shapes.color = Color.GREEN
            drawings.drawCircleWorld(it.movePosition,15F, 100, renderer, shapes)
        }
        clickPositionGamePosition = gameContext.renderer[it.clickPosition.x,it.clickPosition.y, it.clickPosition.z]
        clickDist = geometry.distanceBetweenScreenPositions(
            MouseInfo.getPointerInfo().location.x,
            MouseInfo.getPointerInfo().location.y,
            clickPositionGamePosition.x,
            clickPositionGamePosition.y)

        if (clickDist > 0F){
            shapes.color = Color.YELLOW
            drawings.drawCircleWorld(it.clickPosition,13F, 100, renderer, shapes)
        }
        shapes.color = Color.GREEN
        drawings.drawCircleWorld(it.clickPosition,13F, 100, renderer, shapes)
        shapes.color = Color.WHITE
        drawings.drawCircleWorld(it.movePosition,13F, 100, renderer, shapes)
    }
}
