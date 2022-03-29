package com.leagueofjire.scripts.utils.core

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.leagueofjire.ScreenPosition
import com.leagueofjire.game.GameMinimap
import com.leagueofjire.game.GamePosition
import com.leagueofjire.game.GameRenderer
import com.leagueofjire.scripts.utils.Drawings
import kotlin.math.cos
import kotlin.math.sin

class IDrawings: Drawings {
    override fun drawCircleWorld(position: GamePosition, radius: Float, numPoints: Int, renderer: GameRenderer, shapes: ShapeRenderer) {
        require(numPoints >= 2) { "numPoints must contain at least 4 points" }
        require(numPoints % 2 == 0) { "numPoints must be multiple of 2" }
        val points = FloatArray(numPoints * 2)
        val rest = 4
        val step = Math.PI.toFloat() * 2 / numPoints
        var theta = 0f
        var i = 0
        var screenSpace: ScreenPosition

        while (theta < Math.PI * 2){
            screenSpace = renderer[    position.x + radius * cos(theta.toDouble()).toFloat(),
                    position.y,
                    position.z - radius * sin(theta.toDouble()).toFloat()]
            points[i] = screenSpace.x.toFloat()
            points[i + 1] = screenSpace.y.toFloat()
            theta += step
            i += 2
            if (i % rest == 0){
                if (i == rest){
                    shapes.line(points[i-4], points[i-3], 0F , points[i-2], points[i-1], 0F)
                    continue
                }
                if (i == (numPoints * 2)){
                    shapes.line(points[i-2], points[i-1], 0F , points[0], points[1], 0F)
                }
                shapes.line(points[i-6], points[i-5], 0F , points[i-4], points[i-3], 0F)
                shapes.line(points[i-4], points[i-3], 0F , points[i-2], points[i-1], 0F)
            }
        }
    }

    override fun drawCircleWorldOnMinimap(position: GamePosition, radius: Float, numPoints: Int, renderer: GameMinimap, shapes: ShapeRenderer) {
        require(numPoints >= 2) { "numPoints must contain at least 4 points" }
        require(numPoints % 2 == 0) { "numPoints must be multiple of 2" }
        val points = FloatArray(numPoints * 2)
        val rest = 4
        val step = Math.PI.toFloat() * 2 / numPoints
        var theta = 0f
        var i = 0
        var screenSpace: ScreenPosition

        while (theta < Math.PI * 2){
            screenSpace = renderer[    position.x + radius * cos(theta.toDouble()).toFloat(),
                    position.y,
                    position.z - radius * sin(theta.toDouble()).toFloat()]
            points[i] = screenSpace.x.toFloat()
            points[i + 1] = screenSpace.y.toFloat()
            theta += step
            i += 2
            if (i % rest == 0){
                if (i == rest){
                    shapes.line(points[i-4], points[i-3], 0F , points[i-2], points[i-1], 0F)
                    continue
                }
                if (i == (numPoints * 2)){
                    shapes.line(points[i-2], points[i-1], 0F , points[0], points[1], 0F)
                }
                shapes.line(points[i-6], points[i-5], 0F , points[i-4], points[i-3], 0F)
                shapes.line(points[i-4], points[i-3], 0F , points[i-2], points[i-1], 0F)
            }
        }
    }

    fun Texture.draw(x: Float, y: Float, width: Float, height: Float, sprites: SpriteBatch) = sprites.drawSprite(this, x, y, width, height)

    fun SpriteBatch.drawSprite(
        texture: Texture,
        x: Float, y: Float,
        width: Float = texture.width.toFloat(), height: Float = texture.height.toFloat()
    ) = draw(texture, x, y, width, height, 0, 0, texture.width, texture.height, false, true)

    fun SpriteBatch.setDarkness(percent: Float) = setColor(percent, percent, percent, 1F)

    fun BitmapFont.text(text: String, x: Float, y: Float, batch: SpriteBatch) =
        draw(batch, text, x, y)

    fun BitmapFont.text(text: String, x: Int, y: Int, batch: SpriteBatch) =
        draw(batch, text, x.toFloat(), y.toFloat())

    fun BitmapFont.text(text: String, screenPosition: ScreenPosition, batch: SpriteBatch) =
        text(text, screenPosition.x, screenPosition.y, batch)
}