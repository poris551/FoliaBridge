package com.rune.foliabridge

import com.rune.foliabridge.adapter.BukkitSchedulerAdapter
import com.rune.foliabridge.adapter.FoliaSchedulerAdapter
import com.rune.foliabridge.adapter.SchedulerAdapter
import com.rune.foliabridge.util.PlatformDetector
import kotlinx.coroutines.*
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

object Task {
    private lateinit var scheduler: SchedulerAdapter

    fun init(plugin: Plugin) {
        scheduler = if (PlatformDetector.isFolia) {
            FoliaSchedulerAdapter(plugin)
        } else {
            BukkitSchedulerAdapter(plugin)
        }
    }

    fun runSync(player: Player, block: () -> Unit) {
        scheduler.runSync(player, block)
    }

    fun runAsync(block: () -> Unit) {
        scheduler.runAsync(block)
    }

    fun runLater(player: Player, delayTicks: Long, block: () -> Unit) {
        scheduler.runLater(player, delayTicks, block)
    }

    fun sendMessage(player: Player, message: String) {
        runSync(player) {
            player.sendMessage(message)
        }
    }

    fun launchAsync(block: suspend CoroutineScope.() -> Unit): Job {
        return CoroutineScope(Dispatchers.IO).launch {
            block()
        }
    }

    fun launchSync(player: Player, block: suspend CoroutineScope.() -> Unit): Job {
        return CoroutineScope(Dispatchers.Default).launch {
            runSync(player) {
                runBlocking { block() }
            }
        }
    }
}