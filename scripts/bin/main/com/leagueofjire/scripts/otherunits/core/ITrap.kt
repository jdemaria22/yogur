package com.leagueofjire.scripts.otherunits.core

import com.leagueofjire.scripts.otherunits.Trap
import com.badlogic.gdx.graphics.Color

class ITrap: Trap {
    override var radius = -1
    override var color: Color = Color.MAGENTA
    override var name = ""

    fun createTrap(string: String, radius: Int): Trap{
        this.radius = radius
        this.name = string
        return this
    }
}