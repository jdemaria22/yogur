package com.leagueofjire.game.unit.champion.ai

import com.leagueofjire.game.GamePosition

interface GameChampionAiManager{
    val isMoving: Boolean
    val targetPos: GamePosition
    val serverPos: GamePosition
    val navBegin: GamePosition
    val navEnd: GamePosition
    val ownPosition: GamePosition
    val clickRightPosition : GamePosition
    val castPosition : GamePosition
    val velocity: GamePosition
    val passedWaypoints: Int
    val isDashing: Boolean
    val dashSpeed: Float
    val moveSpeed : Float
}