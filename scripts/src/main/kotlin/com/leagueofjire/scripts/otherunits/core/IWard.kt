package com.leagueofjire.scripts.otherunits.core

import com.leagueofjire.scripts.otherunits.Ward
import com.badlogic.gdx.graphics.Color

class IWard: Ward {
    override var name = ""
    override var radius = -1
    override var color: Color = Color.RED

    fun createWard(string: String, radius: Int, color: Color): Ward {
        this.radius = radius
        this.name = string
        this.color = color
        return this
    }

    override fun toString(): String {
        return "IWard(name='$name', radius=$radius, color=$color)"
    }
}