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
 *
 * Created by dcibo on 5/25/2017.
 */
public class EventHubTrackingPixelHandler implements TrackingPixelHandler {

    private final EventHubClient ehClient;

    public EventHubTrackingPixelHandler(EventHubClient ehClient){
        this.ehClient = ehClient;
    }

    @Override
    public Future<Boolean> persist(PixelHandlerRequest request) throws UnsupportedEncodingException {
        if (request.isSuccess()) {
            EventData eventData = new EventData(request.getJson().toString().getBytes("UTF-8"));
            this.ehClient.send(eventData);
        }
        return new AsyncResult<>(request.isSuccess());
    }
}
