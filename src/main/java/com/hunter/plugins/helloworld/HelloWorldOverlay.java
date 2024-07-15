package com.hunter.plugins.helloworld;

import net.runelite.api.Client;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;

import javax.inject.Inject;
import java.awt.*;

public class HelloWorldOverlay extends Overlay {
    private final Client client;
    private final HelloWorldConfig config;
    private final PanelComponent panelComponent = new PanelComponent();

    @Inject
    private HelloWorldOverlay(Client client, HelloWorldConfig config) {
        this.client = client;
        this.config = config;
        setPosition(OverlayPosition.BOTTOM_RIGHT);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
        setPriority(OverlayPriority.LOW);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        panelComponent.getChildren().clear();
        panelComponent.getChildren().add(LineComponent.builder()
                .left("Greeting:")
                .right(config.greeting())
                .build());
        return panelComponent.render(graphics);
    }
}
