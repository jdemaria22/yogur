import com.badlogic.gdx.graphics.Color
import com.leagueofjire.scripts.utils.core.ITargeting
import com.yogur.panel.scripts.AutoSpell
import java.awt.event.KeyEvent.VK_1
import java.awt.event.KeyEvent.VK_2
import java.awt.event.KeyEvent.VK_3
import java.awt.event.KeyEvent.VK_4

var lastToggle = -1F
val targeting = ITargeting()
eachChampion {
    if(chatOpen.chatOpen) return@eachChampion
    if(!AutoSpell.isEnabled()) return@eachChampion
    val timeToggle = time.seconds
    if (timeToggle - lastToggle >= 0.5F && KeyboardInput.isKeyDown(VK_1) && AutoSpell.isEnabledQ()) {
        lastToggle = timeToggle
        val infoSpell = getInfoSpell(0)
        if (infoSpell.isEmpty) return@eachChampion

        val remaining = gameContext.me.localPlayer.spells[0].readyAtSeconds - time.seconds
        val ready = remaining <= 0
        if (!ready) return@eachChampion

        val spell = infoSpell.get()

        val range = spell.castRange
        val target = targeting.getBestTargetForRange(gameContext.unitManager.champions, gameContext.me.localPlayer, range)
        if  (target.isEmpty) return@eachChampion

        val screeToPositionForCollision = targeting.castPointForCollision(target, spell, gameContext.me.localPlayer, renderer, 0F, false)
        if (screeToPositionForCollision.isEmpty) return@eachChampion
        val thread = Thread{
            run{
                MouseInput.moveAndPressKey(screeToPositionForCollision.get().x, screeToPositionForCollision.get().y, ScanCode.DIK_A)
            }
        }
        thread.start()
        val color = Color.RED
        shapes.color = color
        val y = renderer.height / 2
        shapes.rect(2F, y - 2F, 72F, 18F)
        font.color = color
        font.text("Q - AutoSpell", 5, y)
        return@eachChampion
    }

    if (timeToggle - lastToggle >= 0.5F && KeyboardInput.isKeyDown(VK_2) && AutoSpell.isEnabledW()) {
        val infoSpell = getInfoSpell(1)
        if (infoSpell.isEmpty) return@eachChampion
        val remaining = gameContext.me.localPlayer.spells[1].readyAtSeconds - time.seconds
        val ready = remaining <= 0
        if (!ready) return@eachChampion
        val spell = infoSpell.get()

        val range = spell.castRange + spell.castRadius
        val target = targeting.getBestTargetForRange(gameContext.unitManager.champions, gameContext.me.localPlayer, range)
        if  (target.isEmpty) return@eachChampion

        val screeToPositionForCollision = targeting.castPointForCollision(target, spell,gameContext.me.localPlayer, renderer, 0F, false)

        if (screeToPositionForCollision.isEmpty) return@eachChampion
        val thread = Thread{
            run{
                MouseInput.moveAndPressKey(screeToPositionForCollision.get().x, screeToPositionForCollision.get().y, ScanCode.DIK_S)
            }
        }
        thread.start()
        val color = Color.RED
        shapes.color = color
        val y = renderer.height / 2
        shapes.rect(2F, y - 2F, 72F, 18F)
        font.color = color
        font.text("W - AutoSpell", 5, y)
        return@eachChampion
    }
    if (timeToggle - lastToggle >= 0.5F && KeyboardInput.isKeyDown(VK_3) && AutoSpell.isEnabledE()) {
        val infoSpell = getInfoSpell(2)
        if (infoSpell.isEmpty) return@eachChampion
        val remaining = gameContext.me.localPlayer.spells[2].readyAtSeconds - time.seconds
        val ready = remaining <= 0
        if (!ready) return@eachChampion
        val spell = infoSpell.get()

        val range = spell.castRange + spell.castRadius
        val target = targeting.getBestTargetForRange(gameContext.unitManager.champions, gameContext.me.localPlayer, range)
        if  (target.isEmpty) return@eachChampion

        val screeToPositionForCollision = targeting.castPointForCollision(target, spell,gameContext.me.localPlayer, renderer, 0F, false)

        if (screeToPositionForCollision.isEmpty) return@eachChampion
        val thread = Thread{
            run{
                MouseInput.moveAndPressKey(screeToPositionForCollision.get().x, screeToPositionForCollision.get().y, ScanCode.DIK_D)
            }
        }
        thread.start()
        val color = Color.RED
        shapes.color = color
        val y = renderer.height / 2
        shapes.rect(2F, y - 2F, 72F, 18F)
        font.color = color
        font.text("E - AutoSpell", 5, y)
        return@eachChampion
    }
    if (timeToggle - lastToggle >= 0.5F && KeyboardInput.isKeyDown(VK_4) && AutoSpell.isEnabledR()) {
        val infoSpell = getInfoSpell(3)
        if (infoSpell.isEmpty) return@eachChampion
        val remaining = gameContext.me.localPlayer.spells[3].readyAtSeconds - time.seconds
        val ready = remaining <= 0
        if (!ready) return@eachChampion
        val spell = infoSpell.get()

        val range = spell.castRange + spell.castRadius
        val target = targeting.getBestTargetForRange(gameContext.unitManager.champions, gameContext.me.localPlayer, range)
        if  (target.isEmpty) return@eachChampion

        val screeToPositionForCollision = targeting.castPointForCollision(target, spell,gameContext.me.localPlayer, renderer, 0F, false)

        if (screeToPositionForCollision.isEmpty) return@eachChampion
        val thread = Thread{
            run{
                MouseInput.moveAndPressKey(screeToPositionForCollision.get().x, screeToPositionForCollision.get().y, ScanCode.DIK_F)
            }
        }
        thread.start()
        val color = Color.RED
        shapes.color = color
        val y = renderer.height / 2
        shapes.rect(2F, y - 2F, 72F, 18F)
        font.color = color
        font.text("R - AutoSpell", 5, y)
        return@eachChampion
    }
}