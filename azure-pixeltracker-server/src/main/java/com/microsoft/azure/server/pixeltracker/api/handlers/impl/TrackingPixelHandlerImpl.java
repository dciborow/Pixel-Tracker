package com.microsoft.azure.server.pixeltracker.api.handlers.impl;

import com.microsoft.azure.server.pixeltracker.api.handlers.TrackingPixelHandler;
import com.microsoft.azure.server.pixeltracker.api.model.PixelHandlerRequest;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.concurrent.Future;

/**
 * Tracking Pixel Handler Impl
 * <p>
 * This is the first version of the tracking pixel handler
 * <p>
 * Created by dcibo on 5/24/2017.
 */

public class TrackingPixelHandlerImpl implements TrackingPixelHandler {

    @Override
    public final Future<Boolean> persist(final PixelHandlerRequest request) {
        String queryString = request.getRequest().getQueryString();
        System.out.println("queryString = " + queryString);
        request.setSuccess(true);
        return new AsyncResult<>(request.isSuccess());
    }
}
