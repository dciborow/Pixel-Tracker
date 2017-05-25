package com.microsoft.azure.server.pixeltracker;

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
    List<TrackingPixelHandler> handlers();
}
