package com.microsoft.azure.server.pixeltracker.impl;

import com.microsoft.azure.server.pixeltracker.TrackingPixelConfig;
import com.microsoft.azure.server.pixeltracker.api.handlers.TrackingPixelHandler;
import com.microsoft.azure.server.pixeltracker.api.handlers.impl.TrackingPixelHandlerImpl;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Spring Configuration for Tracking Pixel Server
 * <p>
 * Created by dcibo on 5/25/2017.
 */
public class TrackingPixelConfigImpl implements TrackingPixelConfig {

    /**
     * handlers bean configuration
     *
     * @return list of configured handlers
     */
    @Bean
    public final List<TrackingPixelHandler> handlers() {
        List<TrackingPixelHandler> handlers = new ArrayList<>();
        handlers.add(new TrackingPixelHandlerImpl());
        return handlers;
    }
}
