package com.microsoft.azure.server.pixeltracker.api.handlers.impl;

import com.microsoft.azure.server.pixeltracker.api.handlers.Handler;
import com.microsoft.azure.server.pixeltracker.api.model.Request;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.concurrent.Future;

/**
 * Mock Tracking Pixel Handler for testing
 * <p>
 * Created by dcibo on 5/25/2017.
 */
public class MockTrackingPixelHandlerImpl implements Handler {

    @Override
    public void strategy(Request request) throws Exception {

    }

    @Override
    public Handler setNextOperation(Handler childHandler) {
        return null;
    }

    @Override
    public Future<Boolean> handle(Request request) {
        return new AsyncResult<>(request != null);
    }
}
