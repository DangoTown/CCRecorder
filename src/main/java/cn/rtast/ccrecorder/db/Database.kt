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

import cn.rtast.ccrecorder.dbURI
import java.io.File
import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement

class Database {
    companion object {
        val file = File("action.sqlite")
        var connection: Connection? = null
        var statement: Statement? = null
    }

    fun init() {
        if (!file.exists()) {
            connection = DriverManager.getConnection(dbURI)
            statement = connection?.createStatement()
            statement?.connection?.autoCommit = true
            statement?.execute("CREATE TABLE IF NOT EXISTS actions (id INTEGER PRIMARY KEY AUTOINCREMENT,player TEXT,action TEXT,timestamp INTEGER,x REAL,y REAL,z REAL, content TEXT);")
        }
    }

    fun close() {
        connection?.close()
    }

    fun execute(sql: String) {
        statement?.execute(sql)
    }
}