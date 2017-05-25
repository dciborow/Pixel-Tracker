package com.microsoft.azure.server.pixeltracker.api.handlers.impl;

import com.microsoft.azure.server.pixeltracker.TestWebRequest;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tracking Pixel Handler Test
 * <p>
 * Created by dcibo on 5/25/2017.
 */
class TrackingPixelHandlerImplTest {
    @Test
    void persist() throws Exception {
        TestWebRequest testWebRequest = new TestWebRequest();
        Future<Boolean> persist = new TrackingPixelHandlerImpl().persist(testWebRequest);
        assertTrue(persist.get());
    }

}