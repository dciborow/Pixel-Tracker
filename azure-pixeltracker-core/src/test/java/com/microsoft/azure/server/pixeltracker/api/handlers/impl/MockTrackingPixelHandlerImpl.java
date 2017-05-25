package com.microsoft.azure.server.pixeltracker.api.handlers.impl;

import com.microsoft.azure.server.pixeltracker.api.handlers.TrackingPixelHandler;
import com.microsoft.azure.server.pixeltracker.api.model.PixelHandlerRequest;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.concurrent.Future;

/**
 * Mock Tracking Pixel Handler for testing
 * <p>
 * Created by dcibo on 5/25/2017.
 */
public class MockTrackingPixelHandlerImpl implements TrackingPixelHandler {

    @Override
    public Future<Boolean> handle(PixelHandlerRequest request) {
        return new AsyncResult<>(request != null);
    }
}
