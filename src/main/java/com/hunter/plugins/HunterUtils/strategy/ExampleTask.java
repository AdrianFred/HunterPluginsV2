//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hunter.plugins.HunterUtils.strategy;

import net.runelite.client.config.Config;
import net.runelite.client.plugins.Plugin;

public class ExampleTask extends com.hunter.plugins.HunterUtils.strategy.AbstractTask<Plugin, Config> {
    public ExampleTask(Plugin plugin, Config config) {
        super(plugin, config);
    }

    public boolean validate() {
        return false;
    }

    public void execute() {
        this.interactNpc("Goblin", "Attack", true);
    }
}
