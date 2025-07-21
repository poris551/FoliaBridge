package com.rune.foliabridge.adapter

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

class BukkitSchedulerAdapter(private val plugin: Plugin) : SchedulerAdapter {
    override fun runSync( task: () -> Unit) {
        Bukkit.getScheduler().runTask(plugin, task)
    }

    override fun runSync(player: Player, task: () -> Unit) {
        Bukkit.getScheduler().runTask(plugin, task)
    }

    override fun runSync(location: Location, task: () -> Unit) {
        Bukkit.getScheduler().runTask(plugin, task)
    }

    override fun runAsync(task: () -> Unit) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, task)
    }

    override fun runLater(player: Player, delayTicks: Long, task: () -> Unit) {
        Bukkit.getScheduler().runTaskLater(plugin, task, delayTicks)
    }
}