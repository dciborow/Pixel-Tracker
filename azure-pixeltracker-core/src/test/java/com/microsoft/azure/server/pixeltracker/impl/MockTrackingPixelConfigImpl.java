package com.microsoft.azure.server.pixeltracker.impl;

import com.microsoft.azure.server.pixeltracker.TrackingPixelConfig;
import com.microsoft.azure.server.pixeltracker.api.handlers.TrackingPixelHandler;
import com.microsoft.azure.server.pixeltracker.api.handlers.impl.MockTrackingPixelHandlerImpl;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

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
    public List<TrackingPixelHandler> handlers() {
        List<TrackingPixelHandler> handlers = new ArrayList<>();
        handlers.add(new MockTrackingPixelHandlerImpl());
        return handlers;
    }
}
