package com.microsoft.azure.server.pixeltracker.api.model.impl;

import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.server.pixeltracker.api.handlers.Handler;
import com.microsoft.azure.server.pixeltracker.api.model.PixelTrackerRequest;
import org.springframework.scheduling.annotation.AsyncResult;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.Future;

/**
 * Created by dcibo on 5/25/2017.
 */
public class MockEventHubTrackingPixelHandler implements Handler {
    public MockEventHubTrackingPixelHandler(EventHubClient eventHubClient) {
    }

    @Override
    public void strategy(PixelTrackerRequest request) throws Exception {

    }

    @Override
    public Handler setNextOperation(Handler childHandler) {
        return null;
    }

    @Override
    public Future<Boolean> handle(PixelTrackerRequest request) throws UnsupportedEncodingException {
        return new AsyncResult<>(true);
    }
}
