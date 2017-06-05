package com.microsoft.azure.server.pixeltracker.api;

import com.microsoft.azure.server.pixeltracker.api.handlers.Handler;
import com.microsoft.azure.server.pixeltracker.api.model.PixelTrackerRequestImpl;

import java.util.Map;

/**
 * Created by dcibo on 6/2/2017.
 */
public class AbstractPixel implements Pixel {
    private final Handler handlers;

    public AbstractPixel(Handler handlers) {
        this.handlers = handlers;
    }

    @Override
    public byte[] pixel(Map<String, String> queryParameters) {
        try {
            handlers.handle(new PixelTrackerRequestImpl(queryParameters));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[]{0};
    }
}
