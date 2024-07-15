package com.hunter.plugins.helloworld;

import com.google.inject.Provides;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;

@PluginDescriptor(
        name = "<html><font color=#53a841>[HN]</font> Hello World</html>",
        enabledByDefault = false,
        description = "Starts Hello World",
        tags = {"hello", "world", "test", "example"}
)
public class HelloWorldPlugin extends Plugin {
    @Inject
    private OverlayManager overlayManager;

    @Inject
    private HelloWorldOverlay helloWorldOverlay;

    @Inject
    private HelloWorldConfig config;

    @Override
    protected void startUp() throws Exception {
        overlayManager.add(helloWorldOverlay);
    }

    @Override
    protected void shutDown() throws Exception {
        overlayManager.remove(helloWorldOverlay);
    }

    @Provides
    HelloWorldConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(HelloWorldConfig.class);
    }
}
