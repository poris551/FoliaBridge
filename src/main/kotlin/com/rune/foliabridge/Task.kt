package com.rune.foliabridge

import com.rune.foliabridge.adapter.BukkitSchedulerAdapter
import com.rune.foliabridge.adapter.FoliaSchedulerAdapter
import com.rune.foliabridge.adapter.SchedulerAdapter
import com.rune.foliabridge.util.PlatformDetector.isFolia
import kotlinx.coroutines.*
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

object Task {
    private lateinit var scheduler: SchedulerAdapter

    /** 초기화 */
    fun init(plugin: Plugin) {
        scheduler = if (isFolia) {
            FoliaSchedulerAdapter(plugin)
        } else {
            BukkitSchedulerAdapter(plugin)
        }
    }

    /** 전역 동기 실행 */
    fun runSync(block: () -> Unit) {
        if (isFolia) {
            Bukkit.getOnlinePlayers().forEach { player ->
                runSyncFolia(player, block)
            }
        } else {
            scheduler.runSync(block)
        }
    }

    /** Folia 전용: 플레이어 기준 동기 실행 */
    fun runSyncFolia(player: Player, block: () -> Unit) {
        scheduler.runSync(player, block)
    }

    /** Folia 전용: 위치 기준 동기 실행 */
    fun runSyncFolia(location: Location, block: () -> Unit) {
        scheduler.runSync(location, block)
    }

    /** 전역 비동기 실행 */
    fun runAsync(block: () -> Unit) {
        scheduler.runAsync(block)
    }

    /** 전역 동기 딜레이 실행 */
    fun runLater(player: Player, delayTicks: Long, block: () -> Unit) {
        scheduler.runLater(player, delayTicks, block)
    }

    /** 
     * 플레이어 메세지 전달
     * Bukkit = 그대로 전달
     * Folia = 해당 플레이어의 Region 에서만 작동
     **/
    fun sendMessage(player: Player, message: String) {
        if (isFolia) {
            runSyncFolia(player) {
                player.sendMessage(message)
            }
        } else {
            player.sendMessage(message)
        }
    }

    /** 코루틴 전역 동기 실행 **/
    fun launchSync(block: suspend CoroutineScope.() -> Unit): Job {
        return CoroutineScope(Dispatchers.Default).launch {
            block()
        }
    }

    /** 코루틴 전역 비동기 실행 **/
    fun launchAsync(block: suspend CoroutineScope.() -> Unit): Job {
        return CoroutineScope(Dispatchers.IO).launch {
            block()
        }
    }

}