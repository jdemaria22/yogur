import com.leagueofjire.scripts.utils.core.IDrawings
import com.leagueofjire.scripts.utils.core.ITargeting
import com.yogur.panel.scripts.CooldownTracker
import kotlin.collections.HashMap

val targeting = ITargeting()
val drawings = IDrawings()
val sru_chaosminionmelee = 40F
val sru_chaosminionranged = 38F
val sru_chaosminionsiege = 55F
val sru_chaosminionsuper = 77F
val hashMap:HashMap<String, Float> = HashMap()
hashMap["sru_chaosminionmelee"] = sru_chaosminionmelee
hashMap["sru_chaosminionranged"] = sru_chaosminionranged
hashMap["sru_chaosminionsiege"] = sru_chaosminionsiege
hashMap["sru_chaosminionsuper"] = sru_chaosminionsuper
eachMinions {
    if (!CooldownTracker.isEnabled()) return@eachMinions
    if (!CooldownTracker.minionsTrackerIsEnabled()) return@eachMinions
    if (!isAlive) return@eachMinions
    if (!isVisible) return@eachMinions
    if (team == getLocalPlayer().team) return@eachMinions
    val damage = getLocalPlayer().baseAttack + getLocalPlayer().bonusAttack
    val effectiveDamage = targeting.effectiveDamage(damage, armor)
    if (health <= effectiveDamage) {
        shapes.color = Color.CYAN
        drawings.drawCircleWorld(position,50F, 100, renderer, shapes)
    }
}
