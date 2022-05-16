package com.example.quakestatistics.parser

import com.example.quakestatistics.model.MatchItem
import com.example.quakestatistics.model.QuakeLogConstSingleton
import com.example.quakestatistics.model.UsersStats

class LogParser private constructor(logLines: List<String>) {

    private val logLines = ArrayList<String>()
    private val matchList = ArrayList<MatchItem>()

    private var serverName : String = ""
    private var killCount = 0
    private val userListStats = UsersStats()
    private val killsMode = HashMap<Int, Int>()
    private var isGameStart = false

    init {
        this.logLines.clear()
        this.matchList.clear()

        this.logLines.addAll(logLines)
        parseLog()
    }

    fun updateList(logLines: List<String>){
        this.logLines.clear()
        this.logLines.addAll(logLines)
    }

    fun getMatchList() : ArrayList<MatchItem> {
        return matchList
    }

    private fun parseLog(){


        logLines.forEach {
            when {
                it.contains(QuakeLogConstSingleton.EVENT_INIT) -> {
                    if (isGameStart){
                        endGameTreatment(
                            serverName,
                            killCount,
                            userListStats,
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
                        userListStats,
                        killsMode
                    )
                }
                it.contains(QuakeLogConstSingleton.EVENT_CLI_INF) -> {
                    val ind = getTagSingleValue(it, QuakeLogConstSingleton.REG_USER_INDEX).toInt()
                    val name = getTagSingleValue(it, QuakeLogConstSingleton.REG_USER_NAME)
                    userListStats.addUser(ind, name)
                }
                it.contains(QuakeLogConstSingleton.EVENT_KILL) -> {
                    killCount++

                    val (killer, killed, mod) =
                        getMatchResult(it, QuakeLogConstSingleton.REG_KILL_EVENT)!!.destructured

                    if (killer == QuakeLogConstSingleton.WORLD_INDEX || killer == killed)
                        userListStats.addSuicideToUser(killed.toInt())
                    else {
                        userListStats.addKillToUser(killer.toInt())
                        userListStats.addDeathToUser(killed.toInt())
                    }

                    if (killsMode.containsKey(mod.toInt())){
                        killsMode[mod.toInt()] = killsMode[mod.toInt()]!! + 1
                    } else {
                        killsMode[mod.toInt()] = 1
                    }
                }
            }
        }
    }

    private fun endGameTreatment(serverName: String,
                                 killCount: Int,
                                 usersStats: UsersStats,
                                 killsMode: Map<Int, Int>,
                                 isGameCorrupted: Boolean = false){
        matchList.add(MatchItem(
                        serverName,
                        usersStats.getUserList().size.toString(),
                        killCount.toString(),
                        isGameCorrupted,
                        usersStats))
        this.killCount = 0
        this.userListStats.clear()
        this.killsMode.clear()
        isGameStart = false
    }

    private fun getTagSingleValue(log: String, regex: String): String {
        return getMatchResult(log, regex)!!.destructured.component1()
    }

    private fun getMatchResult(log: String, regex: String) = regex.toRegex().find(log)

    companion object : SingletonHolder<LogParser, List<String>>(::LogParser)
}