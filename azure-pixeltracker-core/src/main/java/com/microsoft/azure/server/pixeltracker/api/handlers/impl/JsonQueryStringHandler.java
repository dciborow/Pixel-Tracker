package com.microsoft.azure.server.pixeltracker.api.handlers.impl;

import com.microsoft.azure.server.pixeltracker.api.handlers.TrackingPixelHandler;
import com.microsoft.azure.server.pixeltracker.api.model.PixelHandlerRequest;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.concurrent.Future;

/**
 * Tracking Pixel Handler Impl
 * <p>
 * This is the first version of the tracking pixel handler
 * <p>
 * Created by dcibo on 5/24/2017.
 */

public class JsonQueryStringHandler implements TrackingPixelHandler {

    private TrackingPixelHandler childHandler;

    public JsonQueryStringHandler(TrackingPixelHandler childHandler) {
        assert childHandler != null : "Child handler not expected to be null.";
        this.childHandler = childHandler;
    }

    @Override
    public final Future<Boolean> handle(final PixelHandlerRequest request) throws UnsupportedEncodingException {
        String queryString = request.getQueryString();
        if (queryString.length() <= 0) request.setSuccess(false);
        else {
            Arrays.stream(queryString.split("&"))
                    .forEach(s -> {
                        String[] value = s.split("=");
                        if (value.length > 1) request.put(value[0], value[1]);
                    });
            request.setSuccess(true);
        }
        return this.childHandler.handle(request);
    }
}
