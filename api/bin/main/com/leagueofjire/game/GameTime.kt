package com.leagueofjire.game

interface GameTime : GameState {
	
	val seconds: Float
	var gamePing: Int
}