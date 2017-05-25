package com.microsoft.azure.server.pixeltracker.api.handlers.impl;

import com.microsoft.azure.server.pixeltracker.TestWebRequest;
import com.microsoft.azure.server.pixeltracker.api.model.impl.PixelHandlerRequestImpl;
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
        PixelHandlerRequestImpl pixelHandlerRequest = new PixelHandlerRequestImpl(testWebRequest);
        Future<Boolean> persist = new TrackingPixelHandlerImpl().persist(pixelHandlerRequest);
        assertTrue(persist.get());
    }

}