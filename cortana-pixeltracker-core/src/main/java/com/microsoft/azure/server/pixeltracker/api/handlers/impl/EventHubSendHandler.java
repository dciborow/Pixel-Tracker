/*
 * Copyright (c) Microsoft. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package com.microsoft.azure.server.pixeltracker.api.handlers.impl;

import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventhubs.spring.EventHubAutoConfiguration;
import com.microsoft.azure.server.pixeltracker.api.model.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.CompletableFuture;

public class EventHubSendHandler extends AbstractHandler {
    private static Logger logger = LogManager.getLogger();

    @Autowired
    private EventHubAutoConfiguration ehClient;
    private final String ENCODING = "UTF-8";

    public EventHubSendHandler(EventHubAutoConfiguration ehClient) {
        assert ehClient != null;
        this.ehClient = ehClient;
    }

    @Override
    public void strategy(Request request) throws Exception {
        logger.trace("Event hub Send Strategy");
        if (request.isSuccess()) sendWithEventHubClient(request);
    }

    private CompletableFuture<Void> sendWithEventHubClient(Request request) throws UnsupportedEncodingException {
        return this.ehClient.eventHubClient().send(eventDataFrom(request));
    }

    private EventData eventDataFrom(Request request) throws UnsupportedEncodingException {
        return new EventData(request.getBytes(ENCODING));
    }

}
