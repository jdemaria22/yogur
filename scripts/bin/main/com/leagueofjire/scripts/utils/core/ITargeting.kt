package com.leagueofjire.scripts.utils.core

import com.leagueofjire.ScreenPosition
import com.leagueofjire.core.game.unit.IGamePosition
import com.leagueofjire.core.game.unit.IGameUnit
import com.leagueofjire.core.game.unit.champion.spell.SpellInfo
import com.leagueofjire.game.GamePosition
import com.leagueofjire.game.GameRenderer
import com.leagueofjire.game.GameTime
import com.leagueofjire.game.unit.GameUnit
import com.leagueofjire.game.unit.champion.buff.GameChampionBuff
import com.leagueofjire.scripts.utils.Targeting
import com.leagueofjire.scripts.utils.Vector
import it.unimi.dsi.fastutil.objects.ObjectList
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.util.Optional
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min

class ITargeting : Targeting {
    private var isAutoSpelling = false
    private var castTimetoWait: Float = 0.0F
    private val geometry = IGeometry()
    override fun getBestTarget(champions: ObjectList<IGameUnit>, localPlayer: IGameUnit): Optional<GameUnit> {
        var lastHealth = Float.MAX_VALUE
        var target: Optional<GameUnit> = Optional.empty()
        champions
            .forEach {
                if (!it.isAlive) return@forEach
                if (!it.isVisible) return@forEach
                if (!it.isTargetable) return@forEach
                if (it.team == localPlayer.team) return@forEach
                if (!isInBasicAttackRange(it, localPlayer)) return@forEach
                if (lastHealth >= it.health){
                    lastHealth = it.health
                    target =  Optional.of(it)
                }
            }
        if (target.isEmpty) return Optional.empty()
        return target
    }

    override fun getBestTargetForRange(champions: ObjectList<IGameUnit>, localPlayer: IGameUnit, range: Float): Optional<IGameUnit> {
        var lastHealth = Float.MAX_VALUE
        var lastDistance = Float.MAX_VALUE
        var target: Optional<IGameUnit> = Optional.empty()
        champions
            .forEach {
                if (!it.isTargetable) return@forEach
                if (!it.isAlive) return@forEach
                if (!it.isVisible) return@forEach
                if (it.team == localPlayer.team) return@forEach
                if (!isInNormalSkillRange(it, localPlayer, range)) return@forEach
                val distance =  geometry.distanceBetweenTargets(localPlayer.position, it.position)
                if (lastDistance >= distance){
                    lastDistance = distance
                    if (lastHealth >= it.health){
                        lastHealth = it.health
                        target =  Optional.of(it)
                    }
                }
            }
        if (target.isEmpty) return Optional.empty()
        return target
    }

    override fun getBestTargetForRangeArea(champions: ObjectList<IGameUnit>, localPlayer: IGameUnit, range: Float): Optional<IGameUnit> {
        var lastHealth = Float.MAX_VALUE
        var lastDistance = Float.MAX_VALUE
        var target: Optional<IGameUnit> = Optional.empty()
        champions
            .forEach {
                if (!it.isTargetable) return@forEach
                if (!it.isAlive) return@forEach
                if (!it.isVisible) return@forEach
                if (it.team == localPlayer.team) return@forEach
                if (!isInNormalSkillRange(it, localPlayer, range)) return@forEach
                val distance =  geometry.distanceBetweenTargets(localPlayer.position, it.position)
                if (lastDistance >= distance){
                    lastDistance = distance
                    if (lastHealth >= it.health){
                        lastHealth = it.health
                        target =  Optional.of(it)
                    }
                }
            }
        if (target.isEmpty) return Optional.empty()
        return target
    }

    override fun getBestMonster(monsters: ObjectList<IGameUnit>, localPlayer: IGameUnit): Optional<GameUnit> {
        var lastHealth = Float.MAX_VALUE
        var target: Optional<GameUnit> = Optional.empty()
        monsters
            .forEach {
                if (!it.isAlive) return@forEach
                if (!it.isVisible) return@forEach
                if (!it.isTargetable) return@forEach
                if (it.team == localPlayer.team) return@forEach
                if (!isInBasicAttackRange(it, localPlayer)) return@forEach
                if (lastHealth >= it.health){
                    lastHealth = it.health
                    target =  Optional.of(it)
                }
            }
        return target
    }

    override fun getBestTargetLaneClear(turrets: ObjectList<IGameUnit>, monsters: ObjectList<IGameUnit>,minions: ObjectList<IGameUnit>, champions: ObjectList<IGameUnit>, localPlayer: IGameUnit): Optional<GameUnit> {
        var lastHealthMinion = Float.MAX_VALUE
        var lastHealthChamp = Float.MAX_VALUE
        var lastHealthMonster = Float.MAX_VALUE
        var target: Optional<GameUnit> = Optional.empty()
        runBlocking {
            minions
                .forEach {
                    async {if (!isInBasicAttackRange(it, localPlayer)) return@async
                        if (it.team == localPlayer.team) return@async
                        if (!it.isAlive) return@async
                        if (!it.isVisible) return@async
                        if (!it.isTargetable) return@async
                        if (lastHealthMinion >= it.health){
                            lastHealthMinion = it.health
                            target =  Optional.of(it)
                        }
                    }
                }
            monsters
                .forEach {
                    async {if (!isInBasicAttackRange(it, localPlayer)) return@async
                        if (!it.isAlive) return@async
                        if (!it.isVisible) return@async
                        if (!it.isTargetable) return@async
                        if (it.team == localPlayer.team) return@async
                        if (lastHealthMonster >= it.health){
                            lastHealthMonster = it.health
                            target =  Optional.of(it)
                        }
                    }
                }
            champions
                .forEach {
                    async {if (!isInBasicAttackRange(it, localPlayer)) return@async
                        if (isClone(it)) return@async
                        if (it.team == localPlayer.team) return@async
                        if (!it.isAlive) return@async
                        if (!it.isVisible) return@async
                        if (!it.isTargetable) return@async
                        if (lastHealthChamp >= it.health){
                            lastHealthChamp = it.health
                            target =  Optional.of(it)}
                    }
                }
            turrets
                .forEach {
                    async {
                        if (!isInBasicAttackRange(it, localPlayer)) return@async
                        if (it.team == localPlayer.team) return@async
                        target =  Optional.of(it)
                    }
                }
        }
        return target
    }

    override fun getBestMinion(minions: ObjectList<IGameUnit>, localPlayer: IGameUnit): Optional<GameUnit> {
        var lastHealth = Float.MAX_VALUE
        var target: Optional<GameUnit> = Optional.empty()
        minions
            .forEach {
                if (!it.isAlive) return@forEach
                if (!it.isVisible) return@forEach
                if (!it.isTargetable) return@forEach
                if (it.team == localPlayer.team) return@forEach
                if (!isInBasicAttackRange(it, localPlayer)) return@forEach
                if (lastHealth >= it.health){
                    lastHealth = it.health
                    target =  Optional.of(it)
                }
            }
        return target
    }

    override fun getLastHitMinion(minions: ObjectList<IGameUnit>, localPlayer: IGameUnit): Optional<GameUnit>{
        minions
            .forEach {
                if (!it.isAlive) return@forEach
                if (!it.isVisible) return@forEach
                if (!it.isTargetable) return@forEach
                if (it.team == localPlayer.team) return@forEach
                if (!isInBasicAttackRange(it, localPlayer)) return@forEach
                val damage = localPlayer.baseAttack + localPlayer.bonusAttack
                val effectiveDamage = effectiveDamage(damage, it.armor)
                if (it.health <= effectiveDamage){
                    return Optional.of(it)
                }
            }
        return Optional.empty()
    }

    override fun castPointForCollision(target: Optional<IGameUnit>, spell: SpellInfo, localPlayer: IGameUnit, renderer: GameRenderer, time: Float, targetSpell: Boolean) : Optional<ScreenPosition> {
        if (target.isEmpty) return Optional.empty()
        if (targetSpell) return Optional.of(renderer[target.get().position.x, target.get().position.y, target.get().position.z])
        if (!target.get().aiManager.isMoving) return Optional.of(renderer[target.get().position.x, target.get().position.y, target.get().position.z])
        val ping = 0.054F
        val serverTickTime = 0.033F
        val bottomClickDelay = 0.060F
        var t = Vector.length(Vector.subGamePosition(target.get().position, localPlayer.position)) / spell.speed
        t += spell.delay + ping //try to use casttime
        if (time > 0F && t < time) return Optional.of(renderer[target.get().position.x, target.get().position.y, target.get().position.z])

        val velocity = target.get().aiManager.velocity
        val orientation = Vector.normalize(velocity)
        if (geometry.distanceBetweenTargets(target.get().position, localPlayer.position) > spell.castRange){
            return Optional.empty()
        }
        if (velocity.x == 0F && velocity.x == 0F){
            return Optional.of(renderer[target.get().aiManager.serverPos.x,target.get().aiManager.serverPos.y,target.get().aiManager.serverPos.z])
        }
        val moveSpeedScaleOrientation = Vector.scale(target.get().aiManager.moveSpeed, orientation)
        val moveSpeedScaleOrientationXt = Vector.scale(t, moveSpeedScaleOrientation)
        val predictedPosition = Vector.addGamePosition(target.get().position, moveSpeedScaleOrientationXt)

        return Optional.of(renderer[predictedPosition.x,predictedPosition.y,predictedPosition.z])
    }

    override fun castPointForCircle(target: Optional<IGameUnit>, spell: SpellInfo, localPlayer: IGameUnit, renderer: GameRenderer) : Optional<ScreenPosition> {
        if (target.isEmpty) return Optional.empty()
        if (!target.get().aiManager.isMoving) return Optional.of(renderer[target.get().position.x, target.get().position.y, target.get().position.z])
        val ping = 0.040F
        val serverTickTime = 0.033F
        val bottomClickDelay = 0.060F
        var castTime = spell.delay
        castTime += ping

        val aim = target.get().aiManager

        val velocity = aim.velocity
        val orientation = Vector.normalize(velocity)
        if (geometry.distanceBetweenTargets(target.get().position, localPlayer.position) > spell.castRange){
            return Optional.empty()
        }
        if (velocity.x == 0F && velocity.x == 0F){
            return Optional.of(renderer[target.get().aiManager.serverPos.x,target.get().aiManager.serverPos.y,target.get().aiManager.serverPos.z])
        }
        val moveSpeedScaleOrientation = Vector.scale(aim.moveSpeed, orientation)
        val moveSpeedScaleOrientationXt = Vector.scale(castTime, moveSpeedScaleOrientation)
        val predictedPosition = Vector.addGamePosition(target.get().position, moveSpeedScaleOrientationXt)
        //predictedPosition.y = target.get().position.y
        return Optional.of(renderer[predictedPosition.x,predictedPosition.y,predictedPosition.z])
    }

    fun predictionWayPoints(target: Optional<IGameUnit>, spell: SpellInfo, localPlayer: IGameUnit, renderer: GameRenderer) : Optional<ScreenPosition> {
        if (target.isEmpty) return Optional.empty()
        //if (!target.get().aiManager.isMoving) return Optional.of(renderer[target.get().position.x, target.get().position.y, target.get().position.z])
        val ping = 40; //if u cant get ur ping just do  "; //+ ping / xxxx.f;"
        val flytime_max = if (spell.speed != 0F){
            spell.castRange / spell.speed
        } else 0F

        var path_bounds0 : GamePosition = IGamePosition()
        var path_bounds1 : GamePosition = IGamePosition()
        val gamePositionNull : GamePosition = IGamePosition()

        val t_min = spell.delay + ping / 2000F
        val t_max = flytime_max + spell.delay + ping / 1000F
        var path_time = 0F

        val ppNavStart = target.get().aiManager.navBegin
        val ppNavEnd = target.get().aiManager.navEnd

        var ppNavPtr = ppNavStart
        //find the limits

        while (ppNavPtr != ppNavEnd){
            val cur = renderer[ppNavPtr.x,ppNavPtr.y,ppNavPtr.z]
            val next1 = Vector.addScalar(ppNavPtr, 1)
            val next = renderer[next1.x,next1.y,next1.z]

            val t= geometry.distanceBetweenTargets2D(cur, next)

            if (path_time <= t_min && path_time + t >= t_min)
            {
                path_bounds0 = ppNavPtr;
            }
            if (path_time <= t_max && path_time + t >= t_max)
            {
                path_bounds1 = ppNavPtr;
            }
            if (path_bounds0 != gamePositionNull && path_bounds1 != gamePositionNull)
            {
                break;
            }
            path_time+=t
            ppNavPtr = Vector.addScalar(ppNavPtr, 1)
        }
        //if skill will hit to the target within the boundaries
        if (path_bounds0 != gamePositionNull && path_bounds1 != gamePositionNull){
            ppNavPtr = path_bounds0
            while (true)
            {
                val cur = renderer[ppNavPtr.x,ppNavPtr.y,ppNavPtr.z]
                val next1 = Vector.addScalar(ppNavPtr, 1)
                val next = renderer[next1.x,next1.y,next1.z]
                val dir = Vector.subScreenPosition(next,cur)
                val direction = Vector.normalize2D(dir)
                val extender = target.get().gameplayRadius * target.get().sizeMultiplier
                val distance: Float = spell.castRadius
                val steps = floor( geometry.distanceBetweenTargets2D(cur, next) / distance)
                if (steps.toInt() in 1..999) {
                    var i = 1
                    while (i < steps - 1)
                    {
                        val directionScaleDistance = Vector.scale2D(distance, direction)
                        val directionScaleI = Vector.scale(i.toFloat(), directionScaleDistance)
                        val center : GamePosition = Vector.addGamePosition2D(cur, directionScaleI)
                        val directionScaleExtender = Vector.scale2D(extender, direction)
                        val pt_a = Vector.subGamePosition(center, directionScaleExtender)
                        val pt_b = Vector.addGamePosition(center, directionScaleExtender)
                        val flytime = if (spell.speed != 0F){
                            geometry.distanceBetweenTargets(localPlayer.position, target.get().position) / spell.speed
                        }else 0.0F

                        val t = flytime + spell.delay + (ping / 2000.0F)
                        val arrive_time_a = geometry.distanceBetweenTargets(target.get().position, pt_a) / target.get().aiManager.moveSpeed
                        val arrive_time_b = geometry.distanceBetweenTargets(target.get().position, pt_b) / target.get().aiManager.moveSpeed
                        val predicted_pos = center
                        if (min(arrive_time_a, arrive_time_b) <= t && max(arrive_time_a, arrive_time_b) >= t)
                        {
                            return Optional.of(renderer[predicted_pos.x, predicted_pos.y, predicted_pos.z])
                        }
                        i++
                    }
                }

                if (ppNavPtr == path_bounds1)
                {
                    break
                }
                ppNavPtr = Vector.addScalar(ppNavPtr, 1)
            }
        }
        return Optional.empty()
    }

    fun isClone(target: GameUnit): Boolean{
        return target.level == 0F
    }

    fun isInBasicAttackRange(target: GameUnit, localPlayer: IGameUnit): Boolean{//Esta validación va excelente para Orbwalker
        val entityRadius = target.gameplayRadius * target.sizeMultiplier
        val championRadius = localPlayer.gameplayRadius * localPlayer.sizeMultiplier
        return geometry.distanceBetweenTargets(localPlayer.position, target.position) - entityRadius < localPlayer.attackRange + championRadius
    }

    fun isInSkillShotRange(target: GameUnit, localPlayer: IGameUnit, range: Float): Boolean{//Esta validación va excelente para SkillShot Spell
        val entityRadius = target.gameplayRadius * target.sizeMultiplier
        return geometry.distanceBetweenTargets(localPlayer.position, target.position) - entityRadius < range
    }
    fun isInNormalSkillRange(target: GameUnit, localPlayer: IGameUnit, range: Float): Boolean{//Esta validación va excelente para Area Spell
        return geometry.distanceBetweenTargets(localPlayer.position, target.position)  < range
    }

    companion object {
        const val LETHAL_TEMPO = "assets/perks/styles/precision/lethaltempo/lethaltempo.lua"
        const val HAIL_OF_BLADE = "assets/perks/styles/domination/hailofblades/hailofbladescd.lua"
        const val PASSIVE_GRAVES_1 = "gravesbasicattackammo1"
        const val PASSIVE_GRAVES_2 = "gravesbasicattackammo2"
    }

    fun isLethalTempoActive(buffs: ArrayList<GameChampionBuff>, time: GameTime): Boolean{
        if (buffs.isEmpty()) return false
        val buffFiltered = buffs.filter { it.endTime > time.seconds && it.name == LETHAL_TEMPO && it.count == 6 }
        if (buffFiltered.isEmpty()) return false
        return true
    }

    fun isHailOfBladesActive(buffs: ArrayList<GameChampionBuff>): Boolean{
        if (buffs.isEmpty()) return false
        val buffFiltered = buffs.filter { it.name == HAIL_OF_BLADE }
        if (buffFiltered.isEmpty()) return true
        return false
    }

    fun buffIsAlive(buffStartTime: Float, buffEndTime: Float, time: GameTime): Boolean{
        val gameTime = time.seconds
        return buffStartTime < gameTime && gameTime < buffEndTime
    }

    fun resetAuto(buffs: ArrayList<GameChampionBuff>): Boolean {
        if (buffs.isEmpty()) return false
        val buffFiltered = buffs.filter { it.name == PASSIVE_GRAVES_1 || it.name == PASSIVE_GRAVES_2 }
        if (buffFiltered.isEmpty()) return false
        return true
    }

    fun isCollisioned(minions: ObjectList<IGameUnit>, target: GameUnit, range: Float, localPlayer: IGameUnit, renderer: GameRenderer): Boolean{
        val me = localPlayer.position
        if(target.isAlive){
            minions
                .forEach {
                    if (it.team == localPlayer.team)return@forEach
                    if (!isInSkillShotRange(it,localPlayer,range))return@forEach
                    if (!it.isAlive)return@forEach
                    if (!it.isTargetable)return@forEach
                    if (!it.isVisible)return@forEach
                    if (geometry.pointOnLineSegment(renderer[me.x, me.y ,me.z],renderer[target.position.x, target.position.y ,target.position.z]
                            ,renderer[it.position.x, it.position.y ,it.position.z]
                            , target.gameplayRadius*1)){
                        return true
                    }
                }
        }
        return false
    }

    fun basicAttackNeeded(champion: GameUnit, target: GameUnit): Float{
        val damage = champion.baseAttack + champion.bonusAttack
        val effectiveDamage = effectiveDamage(damage, target.armor)
        return target.health / effectiveDamage
    }
    fun setAutoSpelling(boolean: Boolean, castTime: Float){
        castTimetoWait = castTime
        isAutoSpelling = boolean
    }
    fun isAutoSpelling(): Boolean{
        if (isAutoSpelling){
            Thread.sleep(castTimetoWait.toLong())
        }
        return false
    }
    fun effectiveDamage(damage: Float, resist: Float): Float{
        return if (resist>= 0F){
            damage * 100F / (100 + resist)
        } else damage * (2F - (100F / (100 -resist)))
    }
}