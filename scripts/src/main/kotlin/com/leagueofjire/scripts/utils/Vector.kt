package com.leagueofjire.scripts.utils

import com.leagueofjire.ScreenPosition
import com.leagueofjire.core.game.unit.IGamePosition
import com.leagueofjire.game.GamePosition
import kotlin.math.sqrt

class Vector {
    companion object {
        fun addGamePosition(position: GamePosition, position2: GamePosition): GamePosition {
            return IGamePosition(position.x + position2.x, position.y + position2.y, position.z + position2.z)
        }

        fun addGamePosition2D(position: ScreenPosition, position2: GamePosition): GamePosition {
            return IGamePosition(position.x + position2.x, position.y + position2.y, 0 + position2.z)
        }

        fun subGamePosition(position: GamePosition, position2: GamePosition): GamePosition {
            return IGamePosition(position.x - position2.x, position.y - position2.y, position.z - position2.z)
        }
        fun subScreenPosition(position: ScreenPosition, position2: ScreenPosition): ScreenPosition {
            return ScreenPosition(position.x - position2.x, position.y - position2.y)
        }

        fun normalize(position: GamePosition): GamePosition {
            val l = length(position)
            var x = -1F
            var y = -1F
            var z = -1F
            if (l != 0F){
                val inv = 1.0F / l
                x = position.x * inv
                y = position.y * inv
                z = position.z * inv
            }
            return IGamePosition(x,y,z)
        }
        fun normalize2D(position: ScreenPosition): ScreenPosition {
            val l = length2D(position)
            var x = -1F
            var y = -1F
            if (l != 0F){
                val inv = 1.0F / l
                x = position.x * inv
                y = position.y * inv
            }
            return ScreenPosition(x,y)
        }

        fun scale(s: Float, position: GamePosition): GamePosition {
            return IGamePosition(position.x * s,position.y * s,position.z * s)
        }

        fun scale2D(s: Float, position: ScreenPosition): GamePosition {
            return IGamePosition(position.x * s,position.y * s, 0F)
        }

        fun length2D(position: ScreenPosition): Float{
            return sqrt((position.x.toFloat() * position.x.toFloat()) + (position.y.toFloat() * position.y.toFloat()))
        }
        fun length(position: GamePosition): Float{
            return sqrt((position.x * position.x) + (position.y * position.y) + (position.z * position.z))
        }
        fun length2d(position: GamePosition): Float{
            return sqrt(position.x * position.x + position.z * position.z)
        }
        fun addScalar(position: GamePosition, f: Int): GamePosition{
            position.x += f
            position.y += f
            position.z += f
            return position
        }
    }
}