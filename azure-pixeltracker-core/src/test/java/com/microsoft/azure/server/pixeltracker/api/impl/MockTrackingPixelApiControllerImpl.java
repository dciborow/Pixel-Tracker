package com.microsoft.azure.server.pixeltracker.api.impl;

import com.microsoft.azure.server.pixeltracker.api.TrackingPixelApiController;
import com.microsoft.azure.server.pixeltracker.api.handlers.HandlerList;
import com.microsoft.azure.server.pixeltracker.api.model.impl.MockPixelHandlerRequestImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Mock Tracking Pixel Api Controller for testing
 * <p>
 * Created by dcibo on 5/25/2017.
 */
public class MockTrackingPixelApiControllerImpl implements TrackingPixelApiController {

    private static final byte[] pixel = {Byte.parseByte("0")};
    private final HandlerList handlers;

    public MockTrackingPixelApiControllerImpl(HandlerList handlers) {
        this.handlers = handlers;
    }

    @Override
    public ResponseEntity<byte[]> get(HttpServletRequest request) {
        MockPixelHandlerRequestImpl mockPixelHandlerRequest = new MockPixelHandlerRequestImpl(request);
        this.handlers.getList().forEach(t -> {
            try {
                t.handle(mockPixelHandlerRequest);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });
        return new ResponseEntity<>(pixel, HttpStatus.ACCEPTED);
    }
}
