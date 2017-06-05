package com.microsoft.azure.server.pixeltracker.api.handlers.impl;

import com.microsoft.azure.server.pixeltracker.api.model.PixelTrackerRequest;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Arrays;

/**
 * Tracking Pixel Handler Impl
 * <p>
 * This is the first version of the tracking pixel handler
 * <p>
 * Created by dcibo on 5/24/2017.
 */

public class JsonQueryStringHandler extends TrackingPixelHandler {

    @Override
    public void strategy(PixelTrackerRequest request) throws Exception {

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
    }
}
