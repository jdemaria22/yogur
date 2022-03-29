package com.leagueofjire.scripts.utils.core

import com.leagueofjire.ScreenPosition
import com.leagueofjire.game.GamePosition
import com.leagueofjire.scripts.utils.Geometry
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

class IGeometry : Geometry{

    override fun pointOnLineSegment(position1: ScreenPosition, position2: ScreenPosition, position3: ScreenPosition, radius: Float): Boolean {
        var epsilon = 0F
        epsilon = if(radius != 0F){
            radius
        } else {
            0.001F
        }
        if (position3.x - max(position1.x, position2.x)     > epsilon
            || min(position1.x, position2.x) - position3.x  > epsilon
            || position3.y - max(position1.y, position2.y)  > epsilon
            || min(position1.y, position2.y) - position3.y  > epsilon){

            return false
        }
        if (abs(position2.x - position1.x) < epsilon){
            return abs(position1.x - position3.x) < epsilon || abs(position2.x - position3.x) < epsilon
        }
        if (abs(position2.y - position1.y) < epsilon){
            return abs(position1.y - position3.y) < epsilon || abs(position2.y - position3.y) < epsilon
        }
        val x = position1.x + (position3.y - position1.y) * (position2.x - position1.x) / (position2.y - position1.y)
        val y = position1.y + (position3.x - position1.x) * (position2.y - position1.y) / (position2.x - position1.x)
        return abs(position3.x - x) < epsilon || abs(position3.y - y) < epsilon
    }

    override fun lengthGamePosition(position: GamePosition): Float{
        return sqrt(position.x * position.x + position.y * position.y + position.z * position.z)
    }

    override fun distanceBetweenTargets(position: GamePosition, position2: GamePosition): Float =
        abs((position.x - position2.x).pow(2) +
                abs(position.y - position2.y).pow(2) +
                abs(position.z - position2.z).pow(2)).pow(0.5F)

    override fun distanceBetweenTargets2D(position: ScreenPosition, position2: ScreenPosition): Float =
        abs((position.x.toFloat() - position2.x.toFloat()).pow(2) + abs(position.y.toFloat() - position2.y.toFloat()).pow(2)).pow(0.5F)
    override fun distanceBetweenScreenPositions(x1: Int, y1: Int, x2: Int, y2: Int) : Float{
        return sqrt(((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1)).toFloat())
    }

}