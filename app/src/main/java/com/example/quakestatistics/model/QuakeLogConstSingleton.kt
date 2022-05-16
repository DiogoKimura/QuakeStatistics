package com.example.quakestatistics.model

object QuakeLogConstSingleton {
    // Event Tags
    const val EVENT_INIT = "InitGame: "
    const val EVENT_END = "ShutdownGame:"
    const val EVENT_EXIT = "Exit: "
    const val EVENT_KILL = "Kill: "
    const val EVENT_CLI_CON = "ClientConnect: "
    const val EVENT_CLI_INF = "ClientUserinfoChanged: "

    // Regex Patterns
    const val REG_KILL_EVENT = "$EVENT_KILL(\\d+) (\\d+) (\\d+)"
    const val REG_SERVER_NAME = """\\sv_hostname\\(.*?)\\"""
    const val REG_USER_INDEX = "$EVENT_CLI_INF(\\d+)"
    const val REG_USER_NAME = """n\\(.*?)\\"""

    // Other Const
    const val WORLD_INDEX = "1022"
}