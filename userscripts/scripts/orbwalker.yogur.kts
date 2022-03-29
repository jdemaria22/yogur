import com.leagueofjire.core.game.unit.IGameUnit
import com.leagueofjire.scripts.utils.core.ITargeting
import com.yogur.panel.scripts.Orbwalker
import it.unimi.dsi.fastutil.objects.ObjectList
import java.awt.MouseInfo
import java.awt.event.KeyEvent.VK_SPACE
import java.awt.event.KeyEvent.VK_V
import java.awt.event.KeyEvent.VK_C
import java.awt.event.KeyEvent.VK_PAGE_UP
import java.awt.event.KeyEvent.VK_PAGE_DOWN

import java.util.Optional

private val targeting = ITargeting()
private val toggleKey = VK_SPACE
private val toggleKeyV = VK_V
private val toggleKeyC = VK_C
private val toggleKeyUP = VK_PAGE_UP
private val toggleKeyDOWN = VK_PAGE_DOWN

private var lastMoveCommandT: Int = 0
simpleContext()
render {
	if (KeyboardInput.isKeyDown(toggleKeyDOWN)) {
		hideContext()
	}
	if (KeyboardInput.isKeyDown(toggleKeyUP)) {
		showContext()
	}
	if(chatOpen.chatOpen) return@render
	if(!Orbwalker.isEnabled()) return@render
	if (KeyboardInput.isKeyDown(toggleKey)) {
		orbWalker(gameContext.unitManager.champions, gameContext.me.localPlayer)
		shapes.color = Color.RED
		val y = renderer.height / 2
		shapes.rect(2F, y - 2F, 72F, 18F)
		font.color = Color.RED
		font.text("OrbWalker", 5, y)
		return@render
	}
	if (KeyboardInput.isKeyDown(toggleKeyV)) {
		laneClear(gameContext.unitManager.monsters, gameContext.unitManager.minions, gameContext.me.localPlayer)
		shapes.color = Color.GREEN
		val y = renderer.height / 2
		shapes.rect(2F, y - 2F, 72F, 18F)
		font.color = Color.GREEN
		font.text("LaneClear", 5, y)
		return@render
	}
	if (KeyboardInput.isKeyDown(toggleKeyC)) {
		lastHit(gameContext.unitManager.minions, gameContext.me.localPlayer)
		shapes.color = Color.CYAN
		val y = renderer.height / 2
		shapes.rect(2F, y - 2F, 72F, 18F)
		font.color = Color.CYAN
		font.text("LastHit", 5, y)
		return@render
	}
}

fun orbWalker(champions: ObjectList<IGameUnit>, localPlayer: IGameUnit){
	val target = targeting.getBestTarget(champions, localPlayer)
	val oldPos = MouseInfo.getPointerInfo().location
	if (target.isPresent && canAttack(localPlayer)) {
		val gameToScreenPosition = gameContext.renderer[target.get().position.x, target.get().position.y, target.get().position.z]
		val thread = Thread{
			run{
				MouseInput.mouseMove(gameToScreenPosition.x,gameToScreenPosition.y)
				MouseInput.pressRightClick()
				attackTimer = getTickCount()
				lastMoveCommandT = (getTickCount() + getWindUpTime(localPlayer).toInt())
				Thread.sleep(25)
				MouseInput.mouseMove(oldPos.x, oldPos.y)
				Thread.interrupted()
			}
		}
		thread.start()
		return
	}
	if (canMove()) {
		MouseInput.pressRightClick()
		lastMoveCommandT = (getTickCount() + randomNumber())
		return
	}
}

fun laneClear(monsters: ObjectList<IGameUnit>, minions: ObjectList<IGameUnit>,localPlayer: IGameUnit){
	val oldPos = MouseInfo.getPointerInfo().location
	fun attack(target: Optional<GameUnit>){
		if (canAttack(localPlayer)) {
			val gameToScreenPosition = gameContext.renderer[target.get().position.x, target.get().position.y, target.get().position.z]
			val thread = Thread{
				run{
					MouseInput.mouseMove(gameToScreenPosition.x,gameToScreenPosition.y)
					MouseInput.pressRightClick()
					attackTimer = getTickCount()
					lastMoveCommandT = (getTickCount() + getWindUpTime(localPlayer).toInt())
					Thread.sleep(25)
					MouseInput.mouseMove(oldPos.x, oldPos.y)
					Thread.interrupted()
				}
			}
			thread.start()
		}
	}
	val targetMonster = targeting.getBestMonster(monsters, localPlayer)
	if (targetMonster.isPresent){
		attack(targetMonster)
	}
	val targetMinion = targeting.getBestMinion(minions, localPlayer)
	if (targetMinion.isPresent){
		attack(targetMinion)
	}
	if (canMove()) {
		MouseInput.pressRightClick()
		lastMoveCommandT = getTickCount() + randomNumber()
	}
}

fun lastHit(minions: ObjectList<IGameUnit>,localPlayer: IGameUnit){
	val target = targeting.getLastHitMinion(minions, localPlayer)
	if (target.isEmpty) return
	val oldPos = MouseInfo.getPointerInfo().location
	if (canAttack(localPlayer)) {
		val gameToScreenPosition = gameContext.renderer[target.get().position.x, target.get().position.y, target.get().position.z]
		val thread = Thread{
			run{
				MouseInput.mouseMove(gameToScreenPosition.x,gameToScreenPosition.y)
				MouseInput.pressRightClick()
				attackTimer = getTickCount()
				lastMoveCommandT = (getTickCount() + getWindUpTime(localPlayer).toInt())
				Thread.sleep(25)
				MouseInput.mouseMove(oldPos.x, oldPos.y)
				Thread.interrupted()
			}
		}
		thread.start()
		return
	}
	if (canMove()) {
		MouseInput.pressRightClick()
		lastMoveCommandT = (getTickCount() + randomNumber())
	}
}

private var attackTimer: Int = 0
fun canAttack(localPlayer: IGameUnit): Boolean{
	if (localPlayer.name == "graves") {
		if (targeting.isGravesAmmoActive(localPlayer.buffs, time)){
			val attackDelay = 1.0740297F * getAttackDelay(localPlayer) - 716.2381F
			return attackTimer + attackDelay + 45 / 2 < getTickCount()
		}
		return false
	}
	return attackTimer + getAttackDelay(localPlayer) + 45 / 2 < getTickCount()
}

fun getTickCount(): Int {
	return Win32Core.getTickCount()
}

fun randomNumber(): Int{
	return (150..300).random()
}

fun getAttackDelay(localPlayer: IGameUnit): Float{
	return 1000 / getAttackSpeed(localPlayer)
}

fun getWindUpTime(localPlayer: IGameUnit): Float {
	return (1 / getAttackSpeed(localPlayer) * 1000) * localPlayer.info.basicAtkWindup
}

fun canMove(): Boolean {
	return lastMoveCommandT < getTickCount()
}

fun getAttackSpeed(localPlayer: IGameUnit): Float{
	val attackSpeed = gameContext.me.localPlayer.info.attackSpeed * gameContext.me.localPlayer.attackSpeedMulti
	if (attackSpeed <= 2.5F){
		return attackSpeed
	}
	if (targeting.isLethalTempoActive(gameContext.me.localPlayer.buffs, time)){
		return attackSpeed
	}
	return 2.5F
}
