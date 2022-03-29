package com.leagueofjire.scripts.utils

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.leagueofjire.game.GameMinimap
import com.leagueofjire.game.GamePosition
import com.leagueofjire.game.GameRenderer

interface Drawings {

    fun drawCircleWorld(position: GamePosition, radius: Float, numPoints: Int, renderer: GameRenderer, shapes: ShapeRenderer)
    fun drawCircleWorldOnMinimap(position: GamePosition, radius: Float, numPoints: Int, renderer: GameMinimap, shapes: ShapeRenderer)
}