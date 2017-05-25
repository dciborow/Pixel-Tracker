package com.microsoft.azure.server.pixeltracker.api.impl;

import com.microsoft.azure.server.pixeltracker.api.TrackingPixelApiController;
import com.microsoft.azure.server.pixeltracker.api.handlers.TrackingPixelHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Tracking Pixel API Controller Implementation
 * <p>
 * This is the first version of the tracking pixel api controller
 * <p>
 * Created by dcibo on 5/24/2017.
 */
@RestController
public class TrackingPixelApiControllerImpl implements TrackingPixelApiController {

    /**
     * Pixel Constant used for output
     */
    private static final byte[] PIXEL = {Byte.parseByte("0")};

    /**
     * list of handlers executed by api
     */
    private final List<TrackingPixelHandler> handlers;

    /**
     * Tracking Pixel Api Controller Impl
     *
     * @param handlers input list of handles wired with Spring
     */
    @Autowired
    public TrackingPixelApiControllerImpl(List<TrackingPixelHandler> handlers) {
        this.handlers = handlers;
    }

    /**
     * Get Request
     *
     * @param request client web request
     * @return async response of 1x1 pixel
     */
    public final ResponseEntity<byte[]> get(HttpServletRequest request) {
        this.handlers.forEach(t -> t.persist(request));
        return new ResponseEntity<>(PIXEL, HttpStatus.ACCEPTED);
    }
}
