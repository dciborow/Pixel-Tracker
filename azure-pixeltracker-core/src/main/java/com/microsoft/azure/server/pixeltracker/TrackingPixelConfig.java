package com.microsoft.azure.server.pixeltracker;

import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.server.pixeltracker.api.handlers.TrackingPixelHandler;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Tracking Pixel Spring Configuration Interface
 * <p>
 * Created by dcibo on 5/25/2017.
 */
@Configuration
public interface TrackingPixelConfig {

    /**
     * Interface for handlers configuration
     *
     * @return list of handlers configured with Spring
     */
    List<TrackingPixelHandler> handlers() throws Exception;

    EventHubClient ehClient() throws Exception;
}
