package com.leagueofjire.core.game.unit.champion.ai

import com.leagueofjire.core.game.unit.IGamePosition
import com.leagueofjire.core.offsets.Offsets
import com.leagueofjire.game.GamePosition
import com.leagueofjire.game.unit.champion.ai.GameChampionAiManager
import org.jire.kna.attach.AttachedProcess
import org.jire.kna.boolean
import org.jire.kna.float

class IGameChampionAiManager: GameChampionAiManager {
    override var isMoving: Boolean = false
    override var targetPos: GamePosition = IGamePosition()
    override var serverPos: GamePosition = IGamePosition()
    override var navBegin: GamePosition = IGamePosition()
    override var navEnd: GamePosition = IGamePosition()
    override var ownPosition: GamePosition = IGamePosition()
    override var clickRightPosition: GamePosition = IGamePosition()
    override var castPosition : GamePosition = IGamePosition()
    override var velocity: GamePosition = IGamePosition()
    override var passedWaypoints: Int = -1
    override var isDashing: Boolean = false
    override var dashSpeed: Float = 0F
    override var moveSpeed : Float = 0F

    fun load(process: AttachedProcess, base: Int) : Boolean {
        try{
            isMoving = process.boolean( base + Offsets.AiManagerIsMoving)

            isDashing = process.boolean(base + Offsets.AiManagerIsDashing)

            serverPos.x = process.float(base + Offsets.AiManagerServPosition)
            serverPos.y = process.float(base + Offsets.AiManagerServPosition + 4)
            serverPos.z = process.float(base + Offsets.AiManagerServPosition + 8)

            navBegin.x = process.float(base + Offsets.AiManagerStartPath)
            navBegin.y = process.float(base + Offsets.AiManagerStartPath + 4)
            navBegin.z = process.float(base + Offsets.AiManagerStartPath + 8)

            navEnd.x = process.float(base + Offsets.AiManagerEndPath)
            navEnd.y = process.float(base + Offsets.AiManagerEndPath + 4)
            navEnd.z = process.float(base + Offsets.AiManagerEndPath + 8)

            ownPosition.x = process.float(base + Offsets.AiManagerOwnPosition)
            ownPosition.y = process.float(base + Offsets.AiManagerOwnPosition + 4)
            ownPosition.z = process.float(base + Offsets.AiManagerOwnPosition + 8)

            clickRightPosition.x = process.float(base + Offsets.AiManagerClickRightPosition)
            clickRightPosition.y = process.float(base + Offsets.AiManagerClickRightPosition + 4)
            clickRightPosition.z = process.float(base + Offsets.AiManagerClickRightPosition + 8)

            castPosition.x = process.float(base + Offsets.AiManagerCastPosition)
            castPosition.y = process.float(base + Offsets.AiManagerCastPosition + 4)
            castPosition.z = process.float(base + Offsets.AiManagerCastPosition + 8)

            velocity.x = process.float(base + Offsets.AiManagerVelocity)
            velocity.y = process.float(base + Offsets.AiManagerVelocity + 4)
            velocity.z = process.float(base + Offsets.AiManagerVelocity + 8)

            moveSpeed = process.float(base + Offsets.AiManagerMoveSpeed)

            dashSpeed = process.float(base + Offsets.AiManagerDashSpeed)
            return true
        } catch (ex : Exception){
            println("Error in IGameChampionAiManager.load: " + ex.message)
            return false
        }
    }
}