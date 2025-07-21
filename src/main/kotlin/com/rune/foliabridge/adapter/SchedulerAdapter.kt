package com.rune.foliabridge.adapter

import org.bukkit.Location
import org.bukkit.entity.Player

interface SchedulerAdapter {
    fun runSync(task: () -> Unit)
    fun runSync(player: Player, task: () -> Unit)
    fun runSync(location: Location, task: () -> Unit)
    fun runAsync(task: () -> Unit)
    fun runLater(player: Player, delayTicks: Long, task: () -> Unit)
}