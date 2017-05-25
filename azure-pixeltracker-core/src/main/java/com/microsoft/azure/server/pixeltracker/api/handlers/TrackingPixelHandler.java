package com.microsoft.azure.server.pixeltracker.api.handlers;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Future;

/**
 * Tracking Pixel Handler Interface
 * <p>
 * Created by dcibo on 5/24/2017.
 */
public interface TrackingPixelHandler {
    Future<Boolean> persist(HttpServletRequest request);
}
