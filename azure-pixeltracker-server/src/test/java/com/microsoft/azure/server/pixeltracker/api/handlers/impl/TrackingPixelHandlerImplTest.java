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
    void handle() throws Exception {
        TestWebRequest testWebRequest = new TestWebRequest();
        testWebRequest.setQueryString("test=1&test2=2");
        PixelHandlerRequestImpl pixelHandlerRequest = new PixelHandlerRequestImpl(testWebRequest);
        Future<Boolean> handle = new JsonQueryStringHandler().handle(pixelHandlerRequest);
        assertTrue(handle.get());
        assertTrue(pixelHandlerRequest.getJson().has("test"));
        assertTrue(pixelHandlerRequest.getJson().has("test2"));
        assertTrue(pixelHandlerRequest.getJson().getString("test").equals("1"));
        assertTrue(pixelHandlerRequest.getJson().getString("test2").equals("2"));
    }

}