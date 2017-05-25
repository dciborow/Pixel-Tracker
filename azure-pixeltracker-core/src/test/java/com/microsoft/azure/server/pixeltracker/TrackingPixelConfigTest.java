package com.microsoft.azure.server.pixeltracker;

import com.microsoft.azure.server.pixeltracker.api.handlers.TrackingPixelHandler;
import com.microsoft.azure.server.pixeltracker.impl.MockTrackingPixelConfigImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tracking Pixel Spring Configuration Test
 * <p>
 * Created by dcibo on 5/25/2017.
 */
class TrackingPixelConfigTest {
    @Test
    void handlers() {
        MockTrackingPixelConfigImpl mockTrackingPixelConfig = new MockTrackingPixelConfigImpl();
        List<TrackingPixelHandler> handlers = mockTrackingPixelConfig.handlers();
        assertNotNull(handlers);
    }

}