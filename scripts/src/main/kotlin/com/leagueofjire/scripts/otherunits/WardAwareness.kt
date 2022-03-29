package com.leagueofjire.scripts.otherunits

import com.leagueofjire.game.GamePosition

interface WardAwareness {
    var name: String
    var clickPosition: GamePosition
    var wardPosition: GamePosition
    var movePosition: GamePosition
}