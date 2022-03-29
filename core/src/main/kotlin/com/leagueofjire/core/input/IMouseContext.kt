package com.leagueofjire.core.input

import MouseInput
import com.leagueofjire.ScreenPosition
import com.leagueofjire.input.MouseContext
import java.awt.MouseInfo
import java.awt.Robot
import com.leagueofjire.core.native.UserLib32
import com.sun.jna.platform.win32.WinDef
import java.awt.event.InputEvent

const val MOUSEEVENT_LEFTDOWN = 0x0002;
const val MOUSEEVENTF_LEFTUP = 0x0004;

class IMouseContext(val robot: Robot) : MouseContext {

	override fun moveMouse(x: Int, y: Int) {
		robot.mouseMove(x, y)
	}


	override val position get() = MouseInfo.getPointerInfo().location.run { ScreenPosition(x, y) }

	override fun pressRightClick() {
		MouseInput.pressRightClick()
	}

	override fun pressLeftClick() {
		UserLib32.mouse_event(MOUSEEVENT_LEFTDOWN, 0, 0 , 0, 0)
		UserLib32.mouse_event(MOUSEEVENTF_LEFTUP, 0, 0 , 0, 0)
	}

	override fun blockInput(boolean: Boolean){
		Win32Core.blockInput(boolean)
	}

}