package com.leagueofjire.game.unit

import com.leagueofjire.game.GamePosition

interface GameMissile {
    var missileName: String
    var missileStartPos: GamePosition
    var missileEndPos: GamePosition
    var missileSource: Int
    var missileDestiny: Int
}