package com.example.quakestatistics.parser

import com.example.quakestatistics.model.*

/*
* All the logic was first created on a Kotlin Alone Project
*
* On Every MAtch
* * Everu Player will be added
* * When the player changes his name, that's what it'll be recorded
* * The Stats:
* * * The Score: Kills - Suicide
* * * The Kills: Kill someone
* * * The Deaths: Killed by someone
* * * The Suicide: Killed by <world>
*
* */
class LogParser private constructor(logLines: List<String>) {

    private val logLines = ArrayList<String>()
    private val matchList = ArrayList<MatchItem>()
    private val userList = ArrayList<UserStats>()
    private val killsMode = ArrayList<KillMode>()

    private var serverName: String = ""
    private var killCount = 0
    private var isGameStart = false

    init {
        this.logLines.clear()
        this.matchList.clear()

        this.logLines.addAll(logLines)
        parseLog()
    }

    fun getMatchList(): ArrayList<MatchItem> {
        return matchList
    }

    private fun parseLog() {
        logLines.forEach {
            when {
                it.contains(QuakeLogConstSingleton.EVENT_INIT) -> {
                    if (isGameStart) {
                        endGameTreatment(
                            serverName,
                            killCount,
                            userList,
                            killsMode,
                            true
                        )
                    }
                    isGameStart = true
                    serverName = getTagSingleValue(it, QuakeLogConstSingleton.REG_SERVER_NAME)
                }
                it.contains(QuakeLogConstSingleton.EVENT_END) -> {
                    endGameTreatment(
                        serverName,
                        killCount,
                        userList,
                        killsMode
                    )
                }
                it.contains(QuakeLogConstSingleton.EVENT_CLI_INF) -> {
                    val ind = getTagSingleValue(it, QuakeLogConstSingleton.REG_USER_INDEX).toInt()
                    val name = getTagSingleValue(it, QuakeLogConstSingleton.REG_USER_NAME)
                    addUser(ind, name)
                }
                it.contains(QuakeLogConstSingleton.EVENT_KILL) -> {
                    killCount++

                    val (killer, killed, mod) =
                        getMatchResult(it, QuakeLogConstSingleton.REG_KILL_EVENT)!!.destructured

                    if (killer == QuakeLogConstSingleton.WORLD_INDEX)
                        addSuicideToUser(killed.toInt())
                    else {
                        if (killer != killed) {
                            addKillToUser(killer.toInt())
                            addDeathToUser(killed.toInt())
                        }
                    }

                    var itemExist = false
                    for (i in 0 until killsMode.size) {
                        val item = killsMode[i]
                        if (item.name.ordinal == mod.toInt()) {
                            item.add()
                            itemExist = true
                            continue
                        }

                    }
                    if (!itemExist) killsMode.add(KillMode(KillModeEnum.values()[mod.toInt()]))
                }
            }
        }
    }

    private fun endGameTreatment(
        serverName: String,
        killCount: Int,
        userList: List<UserStats>,
        killsMode: List<KillMode>,
        isGameCorrupted: Boolean = false
    ) {

        val userListToAdd = ArrayList<UserStats>()
        userListToAdd.addAll(userList)
        val killsModeToAdd = ArrayList<KillMode>()
        killsModeToAdd.addAll(killsMode)

        val matchItem = MatchItem(
            serverName,
            userList.size.toString(),
            killCount.toString(),
            isGameCorrupted,
            userListToAdd,
            killsModeToAdd
        )
        matchList.add(matchItem)
        this.killCount = 0
        this.userList.clear()
        this.killsMode.clear()
        isGameStart = false
    }

    private fun getTagSingleValue(log: String, regex: String): String {
        return getMatchResult(log, regex)!!.destructured.component1()
    }

    private fun getMatchResult(log: String, regex: String) = regex.toRegex().find(log)

    private fun addUser(ind: Int, name: String) {
        val user = UserStats(ind, name)
        userList.forEach {
            if (it.ind == user.ind) {
                it.name = user.name
                return
            }
        }
        userList.add(user)
    }

    private fun findUser(ind: Int): UserStats? {
        userList.forEach {
            if (it.ind == ind) return it
        }

        return null
    }

    private fun addKillToUser(ind: Int) = findUser(ind)?.addKill()

    private fun addDeathToUser(ind: Int) = findUser(ind)?.addDeath()

    private fun addSuicideToUser(ind: Int) = findUser(ind)?.addSuicide()

    private fun clear() = userList.clear()

    companion object : SingletonHolder<LogParser, List<String>>(::LogParser)
}