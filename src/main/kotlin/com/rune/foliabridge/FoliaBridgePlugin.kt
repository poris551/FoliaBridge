package com.rune.foliabridge

import org.bukkit.plugin.java.JavaPlugin

class FoliaBridgePlugin : JavaPlugin() {

    override fun onEnable() {
        Task.init(this)
    }

    override fun onDisable() {

    }
}
