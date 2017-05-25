package com.microsoft.azure.server.pixeltracker.api;

import com.microsoft.azure.server.pixeltracker.MockWebRequest;
import com.microsoft.azure.server.pixeltracker.api.impl.MockTrackingPixelApiControllerImpl;
import com.microsoft.azure.server.pixeltracker.impl.MockTrackingPixelConfigImpl;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tracking Pixel Api Controller Test
 * <p>
 * Created by dcibo on 5/25/2017.
 */
class TrackingPixelApiControllerTest {
    @Test
    void get() {
        MockTrackingPixelConfigImpl config = new MockTrackingPixelConfigImpl();
        MockTrackingPixelApiControllerImpl api = new MockTrackingPixelApiControllerImpl(config.handlers());
        ResponseEntity<byte[]> responseEntity = api.get(new MockWebRequest());
        assertEquals(responseEntity.getStatusCode(), HttpStatus.ACCEPTED);
    }

}