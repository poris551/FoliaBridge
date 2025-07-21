package com.rune.foliabridge.adapter

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

class FoliaSchedulerAdapter(private val plugin: Plugin) : SchedulerAdapter {
    private val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun runSync(task: () -> Unit) {
        // Bukkit 전용 호출
    }

    override fun runSync(player: Player, task: () -> Unit) {
        Bukkit.getRegionScheduler().run(plugin, player.location) {
            task()
        }
    }

    override fun runSync(location: Location, task: () -> Unit) {
        Bukkit.getRegionScheduler().run(plugin, location) { _ ->
            task()
        }
    }

    override fun runAsync(task: () -> Unit) {
        coroutineScope.launch {
            task()
        }
    }

    override fun runLater(player: Player, delayTicks: Long, task: () -> Unit) {
        Bukkit.getRegionScheduler().runDelayed(plugin, player.location, {
            task()
        }, delayTicks)
    }
}