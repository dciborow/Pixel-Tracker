package com.microsoft.azure.server.pixeltracker.api.handlers.impl;

import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.server.pixeltracker.api.handlers.TrackingPixelHandler;
import com.microsoft.azure.server.pixeltracker.api.model.PixelHandlerRequest;
import org.springframework.scheduling.annotation.AsyncResult;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.Future;

/**
 * Event Hub Handler for Pixel Tracking
 * <p>
 * Created by dcibo on 5/25/2017.
 */
public class EventHubSendHandler implements TrackingPixelHandler {

    private final EventHubClient ehClient;

    public EventHubSendHandler(EventHubClient ehClient) {
        this.ehClient = ehClient;
    }

    @Override
    public Future<Boolean> handle(PixelHandlerRequest request) throws UnsupportedEncodingException {
        if (request.isSuccess())
            this.ehClient.send(new EventData(request.getBytes("UTF-8")));
        return new AsyncResult<>(request.isSuccess());
    }
}
