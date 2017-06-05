package com.microsoft.azure.server.pixeltracker.api.handlers.impl;

import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.server.pixeltracker.api.model.PixelTrackerRequest;

/**
 * Event Hub Handler for Pixel Tracking
 * <p>
 * Created by dcibo on 5/25/2017.
 */
public class EventHubSendHandler extends TrackingPixelHandler {

    private final EventHubClient ehClient;

    public EventHubSendHandler(EventHubClient ehClient) {
        assert ehClient != null;
        this.ehClient = ehClient;
    }

    @Override
    public void strategy(PixelTrackerRequest request) throws Exception {
        if (request.isSuccess())
            if (this.ehClient == null) request.setSuccess(false);
            else {
                this.ehClient.send(new EventData(request.getBytes("UTF-8")));
            }
    }

}
