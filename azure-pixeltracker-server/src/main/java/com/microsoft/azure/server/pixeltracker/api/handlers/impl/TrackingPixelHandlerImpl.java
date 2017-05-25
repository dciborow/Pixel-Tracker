package com.microsoft.azure.server.pixeltracker.api.handlers.impl;

import com.microsoft.azure.server.pixeltracker.api.handlers.TrackingPixelHandler;
import org.springframework.scheduling.annotation.AsyncResult;

import javax.servlet.http.HttpServletRequest;
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
    public final Future<Boolean> persist(final HttpServletRequest request) {
        String queryString = request.getQueryString();
        System.out.println("queryString = " + queryString);
        return new AsyncResult<>(true);
    }
}
