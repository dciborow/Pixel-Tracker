/*
 * Copyright (c) Microsoft. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package com.microsoft.azure.server.pixeltracker.api.handlers.impl;

import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventhubs.spring.EventHubAutoConfiguration;
import com.microsoft.azure.server.pixeltracker.api.model.Request;

public class EventHubSendHandler extends AbstractHandler {

    private final EventHubAutoConfiguration ehClient;

    public EventHubSendHandler(EventHubAutoConfiguration ehClient) {
        assert ehClient != null;
        this.ehClient = ehClient;
    }

    @Override
    public void strategy(Request request) throws Exception {
        if (request.isSuccess())
            this.ehClient.eventHubClient().send(new EventData(request.getBytes("UTF-8")));

    }

}
