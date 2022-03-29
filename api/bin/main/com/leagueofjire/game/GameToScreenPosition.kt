package com.leagueofjire.game

import com.leagueofjire.ScreenPosition

interface GameToScreenPosition {
	
	operator fun get(x: Float, y: Float, z: Float): ScreenPosition

}