import com.leagueofjire.scripts.utils.core.ITargeting
import com.yogur.panel.scripts.AutoSpell
import java.awt.event.KeyEvent
var lastToggle = -1F
val targeting = ITargeting()
if (getLocalPlayer().name == "graves"){
    eachChampion{
        if(chatOpen.chatOpen) return@eachChampion
        if(!AutoSpell.isEnabledSpecific()) return@eachChampion
        val timeToggle = time.seconds
        
        //pressQ
        if (timeToggle - lastToggle >= 0.5F && KeyboardInput.isKeyDown(KeyEvent.VK_1)) {
            lastToggle = timeToggle
            val infoSpell = getInfoSpell(0)
            if (infoSpell.isEmpty) return@eachChampion
            val remaining = gameContext.me.localPlayer.spells[0].readyAtSeconds - time.seconds
            val ready = remaining <= 0
            if (!ready) return@eachChampion
            val spell = infoSpell.get()
            val range = spell.castRange + spell.castRadius
            val target = targeting.getBestTargetForRange(gameContext.unitManager.champions, gameContext.me.localPlayer, range)
            if  (target.isEmpty) return@eachChampion
            val screeToPositionForCollision = targeting.castPointForCollision(target, spell, gameContext.me.localPlayer, renderer, 0.5F, false)
            if (screeToPositionForCollision.isEmpty) return@eachChampion
            val thread = Thread{
                run{
                    MouseInput.moveAndPressKey(screeToPositionForCollision.get().x, screeToPositionForCollision.get().y, ScanCode.DIK_A)
                }
            }
            thread.start()
            return@eachChampion
        }

        //pressW
        if (timeToggle - lastToggle >= 0.5F && KeyboardInput.isKeyDown(KeyEvent.VK_2)) {
            lastToggle = timeToggle
            val infoSpell = getInfoSpell(1)
            if (infoSpell.isEmpty) return@eachChampion
            val remaining = gameContext.me.localPlayer.spells[1].readyAtSeconds - time.seconds
            val ready = remaining <= 0
            if (!ready) return@eachChampion
            val spell = infoSpell.get()
            val range = spell.castRange + spell.castRadius
            val target = targeting.getBestTargetForRange(gameContext.unitManager.champions, gameContext.me.localPlayer, range)
            if  (target.isEmpty) return@eachChampion
            val screeToPositionForCollision = targeting.castPointForCollision(target, spell, gameContext.me.localPlayer, renderer, 0.5F, false)
            if (screeToPositionForCollision.isEmpty) return@eachChampion
            val thread = Thread{
                run{
                    MouseInput.moveAndPressKey(screeToPositionForCollision.get().x, screeToPositionForCollision.get().y, ScanCode.DIK_S)
                }
            }
            thread.start()
            return@eachChampion
        }

        //pressR
        if (timeToggle - lastToggle >= 0.5F && KeyboardInput.isKeyDown(KeyEvent.VK_4)) {
            lastToggle = timeToggle
            val infoSpell = getInfoSpell(3)
            if (infoSpell.isEmpty) return@eachChampion
            val remaining = gameContext.me.localPlayer.spells[3].readyAtSeconds - time.seconds
            val ready = remaining <= 0
            if (!ready) return@eachChampion
            val spell = infoSpell.get()
            val range = spell.castRange + spell.castRadius
            val target = targeting.getBestTargetForRange(gameContext.unitManager.champions, gameContext.me.localPlayer, range)
            if  (target.isEmpty) return@eachChampion
            val screeToPositionForCollision = targeting.castPointForCollision(target, spell, gameContext.me.localPlayer, renderer, 0.6F, false)
            if (screeToPositionForCollision.isEmpty) return@eachChampion
            val thread = Thread{
                run{
                    MouseInput.moveAndPressKey(screeToPositionForCollision.get().x, screeToPositionForCollision.get().y, ScanCode.DIK_F)
                }
            }
            thread.start()
            return@eachChampion
        }
    }
}