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

    private static final byte[] pixel = {Byte.parseByte("0")};

    private final List<TrackingPixelHandler> handlers;

    @Autowired
    public TrackingPixelApiControllerImpl(List<TrackingPixelHandler> handlers) {
        this.handlers = handlers;
    }

    public ResponseEntity<byte[]> get(HttpServletRequest request) {
        this.handlers.forEach(t -> t.persist(request));
        return new ResponseEntity<>(pixel, HttpStatus.ACCEPTED);
    }
}
