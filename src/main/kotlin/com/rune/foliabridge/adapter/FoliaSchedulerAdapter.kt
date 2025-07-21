package com.rune.foliabridge.adapter

import io.papermc.paper.threadedregions.scheduler.ScheduledTask
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

class FoliaSchedulerAdapter(private val plugin: Plugin) : SchedulerAdapter {
    override fun runSync(player: Player, task: () -> Unit) {
        Bukkit.getRegionScheduler().run(plugin, player.location) { _: ScheduledTask ->
            task()
        }
    }

    override fun runAsync(task: () -> Unit) {
        Bukkit.getGlobalRegionScheduler().run(plugin) { _: ScheduledTask ->
            task()
        }
    }

    override fun runLater(player: Player, delayTicks: Long, task: () -> Unit) {
        Bukkit.getRegionScheduler().runDelayed(plugin, player.location, { _: ScheduledTask ->
            task()
        }, delayTicks)
    }
}