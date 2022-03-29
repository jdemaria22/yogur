package com.leagueofjire.scripts.utils

import com.leagueofjire.ScreenPosition
import com.leagueofjire.game.GamePosition

interface Geometry {

    fun pointOnLineSegment(position1: ScreenPosition, position2: ScreenPosition, position3: ScreenPosition, radius: Float): Boolean

    fun lengthGamePosition(position: GamePosition): Float

    fun distanceBetweenTargets(position: GamePosition, position2: GamePosition) : Float

    fun distanceBetweenTargets2D(position: ScreenPosition, position2: ScreenPosition) : Float

    fun distanceBetweenScreenPositions(x1: Int, y1: Int, x2: Int, y2: Int) : Float

}