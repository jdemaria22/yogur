package com.leagueofjire.scripts.otherunits.core

import com.leagueofjire.core.game.unit.IGamePosition
import com.leagueofjire.game.GamePosition
import com.leagueofjire.scripts.otherunits.WardAwareness

class IWardAwareness: WardAwareness {
    override var name = ""
    override var clickPosition: GamePosition =  IGamePosition()
    override var wardPosition: GamePosition =  IGamePosition()
    override var movePosition: GamePosition =  IGamePosition()

    fun createWardAwareness(name: String, clickPosition: IGamePosition, wardPosition: IGamePosition, movePosition: IGamePosition): WardAwareness{
        this.name = name
        this.clickPosition = clickPosition
        this.wardPosition = clickPosition
        this.movePosition = clickPosition
        return this
    }
}