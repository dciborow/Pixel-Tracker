package com.microsoft.azure.server.pixeltracker.impl;

import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.server.pixeltracker.TrackingPixelConfig;
import com.microsoft.azure.server.pixeltracker.api.handlers.Handler;
import com.microsoft.azure.server.pixeltracker.api.handlers.impl.MockTrackingPixelHandlerImpl;
import org.springframework.context.annotation.Bean;

/**
 * Mock Tracking Pixel Api Config for testing
 * <p>
 * Created by dcibo on 5/25/2017.
 */
public class MockTrackingPixelConfigImpl implements TrackingPixelConfig {

    /**
     * Configuration for Handles
     *
     * @return list of handlers configured
     */
    @Bean
    public Handler handlers() {
        return new MockTrackingPixelHandlerImpl();
    }

    @Override
    public EventHubClient ehClient() throws Exception {
        return null;
    }
}
