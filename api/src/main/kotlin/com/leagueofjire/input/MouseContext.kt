package com.leagueofjire.input

import com.leagueofjire.ScreenPosition
interface MouseContext {

	fun pressRightClick()

	fun pressLeftClick()

	fun moveMouse(x: Int, y: Int)

	val position: ScreenPosition

	fun blockInput(boolean: Boolean)

}