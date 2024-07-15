package com.hunter.plugins.helloworld;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("helloworld")
public interface HelloWorldConfig extends Config {
    @ConfigItem(
            keyName = "greeting",
            name = "Greeting",
            description = "The greeting to display"
    )
    default String greeting() {
        return "Hello, RuneLite!";
    }
}
