package com.microsoft.azure.server.pixeltracker.api.impl;

import com.microsoft.azure.server.pixeltracker.TestWebRequest;
import com.microsoft.azure.server.pixeltracker.api.handlers.TrackingPixelHandler;
import com.microsoft.azure.server.pixeltracker.impl.TrackingPixelConfigImpl;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit5 tests for Tracking Pixel API Controller Impl
 * <p>
 * Created by dcibo on 5/25/2017.
 */
class TrackingPixelApiControllerImplTest {

    @Test
    void get() {
        List<TrackingPixelHandler> handlers = new TrackingPixelConfigImpl().handlers();

        ResponseEntity<byte[]> responseEntity = new TrackingPixelApiControllerImpl(handlers)
                .get(new TestWebRequest());

        assertEquals(responseEntity.getStatusCode(), HttpStatus.ACCEPTED);
    }

}