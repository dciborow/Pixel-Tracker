package com.microsoft.azure.server.pixeltracker;

import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.server.pixeltracker.api.handlers.TrackingPixelHandler;
import com.microsoft.azure.server.pixeltracker.api.handlers.impl.EventHubTrackingPixelHandler;
import com.microsoft.azure.server.pixeltracker.api.handlers.impl.TrackingPixelHandlerImpl;
import com.microsoft.azure.server.pixeltracker.api.model.impl.MockEventHubTrackingPixelHandler;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dcibo on 5/25/2017.
 */
public class TestTrackingPixelConfigImpl implements TrackingPixelConfig {
    @Override
    @Bean
    public List<TrackingPixelHandler> handlers() throws Exception {
        List<TrackingPixelHandler> handlers = new ArrayList<>();
        handlers.add(new TrackingPixelHandlerImpl());
        handlers.add(new MockEventHubTrackingPixelHandler(ehClient()));
        return handlers;
    }

    @Override
    @Bean
    public EventHubClient ehClient() {
        return null;
    }
}
