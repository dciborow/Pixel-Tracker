package com.microsoft.azure.server.pixeltracker.api.handlers;

import com.microsoft.azure.server.pixeltracker.MockWebRequest;
import com.microsoft.azure.server.pixeltracker.api.handlers.impl.MockTrackingPixelHandlerImpl;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by dcibo on 5/25/2017.
 */
class TrackingPixelHandlerTest {
    @Test
    void persist() throws Exception {
        MockTrackingPixelHandlerImpl handler = new MockTrackingPixelHandlerImpl();
        MockWebRequest mockWebRequest = new MockWebRequest();
        Future<Boolean> persist = handler.persist(mockWebRequest);
        assertTrue(persist.get());
    }

}