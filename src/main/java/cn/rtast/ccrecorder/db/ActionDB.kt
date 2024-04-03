/*
 * Copyright 2024 RTAkland
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */


package cn.rtast.ccrecorder.db

import cn.rtast.ccrecorder.CCRecorder
import cn.rtast.ccrecorder.enums.Action
import net.minecraft.server.network.ServerPlayerEntity

object ActionDB {

    private fun appendAction(action: Action, player: ServerPlayerEntity, content: String) {
        val actionName = action.name.lowercase()
        val playerName = player.name.string
        val timestamp = System.currentTimeMillis() / 1000
        val x = player.x
        val y = player.y
        val z = player.z
        println(actionName + playerName + timestamp + x + y + z + content)
        CCRecorder.database.execute("INSERT INTO actions (player, action, timestamp, x, y, z, content) values ('$playerName','$actionName', $timestamp, $x, $y, $z, '$content')")
    }

    fun appendJoin(player: ServerPlayerEntity, content: String) {
        this.appendAction(Action.Join, player, content)
    }

    fun appendRemove(player: ServerPlayerEntity, content: String) {
        this.appendAction(Action.Remove, player, content)
    }

    fun appendMessage(player: ServerPlayerEntity, content: String) {
        this.appendAction(Action.Message, player, content)
    }

    fun appendCommand(player: ServerPlayerEntity, content: String) {
        this.appendAction(Action.Command, player, content)
    }
}