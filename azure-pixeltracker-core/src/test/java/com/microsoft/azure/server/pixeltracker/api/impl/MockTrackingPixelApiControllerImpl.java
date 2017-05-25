package com.microsoft.azure.server.pixeltracker.api.impl;

import com.microsoft.azure.server.pixeltracker.api.TrackingPixelApiController;
import com.microsoft.azure.server.pixeltracker.api.handlers.TrackingPixelHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Mock Tracking Pixel Api Controller for testing
 * <p>
 * Created by dcibo on 5/25/2017.
 */
public class MockTrackingPixelApiControllerImpl implements TrackingPixelApiController {

    private static final byte[] pixel = {Byte.parseByte("0")};
    private final List<TrackingPixelHandler> handlers;

    public MockTrackingPixelApiControllerImpl(List<TrackingPixelHandler> handlers) {
        this.handlers = handlers;
    }

    @Override
    public ResponseEntity<byte[]> get(HttpServletRequest request) {
        this.handlers.forEach(t -> t.persist(request));
        return new ResponseEntity<>(pixel, HttpStatus.ACCEPTED);
    }
}
