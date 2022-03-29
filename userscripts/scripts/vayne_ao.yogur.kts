import com.leagueofjire.scripts.utils.core.ITargeting
import com.yogur.panel.scripts.AutoSpell
import java.awt.event.KeyEvent
val onMinimap = true
val onWorld = true
var lastToggle = -1F
val targeting = ITargeting()
if (getLocalPlayer().name == "vayne"){
    eachChampion{
        if(chatOpen.chatOpen) return@eachChampion
        if(!AutoSpell.isEnabledSpecific()) return@eachChampion
        val timeToggle = time.seconds

        if (timeToggle - lastToggle >= 0.5F && KeyboardInput.isKeyDown(KeyEvent.VK_3)) {
            lastToggle = timeToggle
            val infoSpell = getInfoSpell(2)
            if (infoSpell.isEmpty) return@eachChampion
            val remaining = gameContext.me.localPlayer.spells[2].readyAtSeconds - time.seconds
            val ready = remaining <= 0
            if (!ready) return@eachChampion
            val spell = infoSpell.get()
            val range = spell.castRange + spell.castRadius
            val target = targeting.getBestTargetForRange(gameContext.unitManager.champions, gameContext.me.localPlayer, range)
            if  (target.isEmpty) return@eachChampion
            val thread = Thread{
                run{
                    MouseInput.moveAndPressKey(renderer[target.get().position.x, target.get().position.y, target.get().position.z].x, renderer[target.get().position.x, target.get().position.y, target.get().position.z].y, ScanCode.DIK_E)
                }
            }
            thread.start()
            return@eachChampion
        }
    }
}