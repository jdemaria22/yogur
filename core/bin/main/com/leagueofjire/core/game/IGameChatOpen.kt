package com.leagueofjire.core.game

import com.leagueofjire.core.offsets.Offsets
import com.leagueofjire.game.GameChat
import org.jire.kna.attach.AttachedModule
import org.jire.kna.attach.AttachedProcess
import org.jire.kna.boolean
import org.jire.kna.int

object IGameChatOpen : GameChat {

    override var chatOpen = false

    fun update(process: AttachedProcess, base: AttachedModule): Boolean {

        val chatInstance = process.int(base.address + Offsets.Chat).toLong()
        val chatOpenval = process.boolean(chatInstance + Offsets.ChatIsOpen)
        chatOpen = chatOpenval
        return true
    }

    override fun update(): Boolean {

        TODO("Not yet implemented")
    }

}