package com.microsoft.azure.server.pixeltracker;

import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.server.pixeltracker.api.handlers.HandlerList;
import com.microsoft.azure.server.pixeltracker.api.handlers.impl.JsonQueryStringHandler;
import com.microsoft.azure.server.pixeltracker.api.model.impl.MockEventHubTrackingPixelHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dcibo on 5/25/2017.
 */
@Configuration
public class TestTrackingPixelConfigImpl implements TrackingPixelConfig {
    @Override
    @Bean
    public com.microsoft.azure.server.pixeltracker.api.handlers.impl.MockTrackingPixelHandlerImpl handlers() throws Exception {
        return new HandlerList().add(new JsonQueryStringHandler()).add(new MockEventHubTrackingPixelHandler(ehClient()));
    }

    @Override
    @Bean
    public EventHubClient ehClient() {
        return null;
    }
}
