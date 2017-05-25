package com.microsoft.azure.server.pixeltracker.api.handlers;

import com.microsoft.azure.server.pixeltracker.api.model.PixelHandlerRequest;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.Future;

/**
 * Tracking Pixel Handler Interface
 * <p>
 * Created by dcibo on 5/24/2017.
 */
public interface TrackingPixelHandler {
    Future<Boolean> handle(PixelHandlerRequest request) throws UnsupportedEncodingException;
}
