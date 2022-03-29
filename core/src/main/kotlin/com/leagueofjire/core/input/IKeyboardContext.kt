package com.leagueofjire.core.input

import com.leagueofjire.core.native.UserLib32
import com.leagueofjire.input.KeyboardContext
import java.awt.Robot
import KeyboardInput


class IKeyboardContext(val robot: Robot) : KeyboardContext {
	
	override fun state(keyCode: Int) = UserLib32.GetKeyState(keyCode).toInt()
	
	override fun pressed(keyCode: Int) = state(keyCode) < 0
	
	override fun released(keyCode: Int) = !pressed(keyCode)
	
	override fun press(keyCode: Int) = run {
		val rob = Robot()
		rob.keyPress(keyCode)
	}

	override fun release(keyCode: Int) = robot.keyRelease(keyCode)

	override fun pressAndRelease(keyCode: Int) {
		KeyboardInput.pressKey(keyCode)
	}
}