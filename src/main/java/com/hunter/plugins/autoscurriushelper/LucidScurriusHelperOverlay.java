package com.hunter.plugins.autoscurriushelper;

import net.runelite.api.Client;
import net.runelite.api.coords.LocalPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;

import javax.inject.Inject;
import java.awt.*;

public class LucidScurriusHelperOverlay extends Overlay
{
    private final Client client;
    private final LucidScurriusHelperPlugin plugin;
    private final LucidScurriusHelperConfig config;
    private final PanelComponent panelComponent = new PanelComponent();

    @Inject
    public LucidScurriusHelperOverlay(Client client, LucidScurriusHelperPlugin plugin, LucidScurriusHelperConfig config)
    {
        this.client = client;
        this.plugin = plugin;
        this.config = config;
        setPosition(OverlayPosition.TOP_LEFT);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
        setPriority(OverlayPriority.HIGH);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        panelComponent.getChildren().clear();

        panelComponent.getChildren().add(LineComponent.builder()
                .left("Hunter Auto Scurrius")
                .leftColor(Color.GREEN)
                .build());

        panelComponent.getChildren().add(LineComponent.builder()
                .left("Plugin Active")
                .right(plugin.isActive() ? "Yes" : "No")
                .rightColor(plugin.isActive() ? Color.GREEN : Color.RED)
                .build());

        panelComponent.getChildren().add(LineComponent.builder()
                .left("Dodging")
                .right(plugin.isJustDodged() ? "Yes" : "No")
                .rightColor(plugin.isJustDodged() ? Color.GREEN : Color.RED)
                .build());

        panelComponent.getChildren().add(LineComponent.builder()
                .left("Auto Pray")
                .right(config.autoPray() ? "Enabled" : "Disabled")
                .rightColor(config.autoPray() ? Color.GREEN : Color.RED)
                .build());



        return panelComponent.render(graphics);
    }
}
