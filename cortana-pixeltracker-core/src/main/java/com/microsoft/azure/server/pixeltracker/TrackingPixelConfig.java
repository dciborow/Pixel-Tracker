/*
 * Copyright (c) Microsoft. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package com.microsoft.azure.server.pixeltracker;

import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.server.pixeltracker.api.handlers.Handler;
import org.springframework.context.annotation.Configuration;

@Configuration
public interface TrackingPixelConfig {

    /**
     * Interface for handlers configuration
     *
     * @return list of handlers configured with Spring
     */
    Handler handlers() throws Exception;

    EventHubClient ehClient() throws Exception;
}
