package com.microsoft.azure.server.pixeltracker.api.model.impl;

import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.server.pixeltracker.api.handlers.TrackingPixelHandler;
import com.microsoft.azure.server.pixeltracker.api.model.PixelHandlerRequest;
import org.springframework.scheduling.annotation.AsyncResult;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.Future;

/**
 * Created by dcibo on 5/25/2017.
 */
public class MockEventHubTrackingPixelHandler implements TrackingPixelHandler {
    public MockEventHubTrackingPixelHandler(EventHubClient eventHubClient) {
    }

    @Override
    public Future<Boolean> persist(PixelHandlerRequest request) throws UnsupportedEncodingException {
        return new AsyncResult<>(true);
    }
}
