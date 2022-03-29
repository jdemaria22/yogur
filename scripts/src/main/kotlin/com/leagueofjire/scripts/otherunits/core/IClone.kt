package com.leagueofjire.scripts.otherunits.core

import com.leagueofjire.scripts.otherunits.Clone
import com.badlogic.gdx.graphics.Color

class IClone : Clone {
    override var color: Color = Color.ORANGE
    override var name = ""

    fun createClone(string: String): Clone{
        this.name = string
        return this
    }

    override fun toString(): String {
        return "IClone(color=$color, name='$name')"
    }
}