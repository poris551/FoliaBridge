package com.rune.foliabridge.adapter

import org.bukkit.entity.Player

interface SchedulerAdapter {
    fun runSync(player: Player, task: () -> Unit)
    fun runAsync(task: () -> Unit)
    fun runLater(player: Player, delayTicks: Long, task: () -> Unit)
}