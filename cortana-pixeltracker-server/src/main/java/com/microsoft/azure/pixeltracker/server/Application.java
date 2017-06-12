/*
 * Copyright (c) Microsoft. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package com.microsoft.azure.pixeltracker.server;

import com.microsoft.azure.eventhubs.spring.EventHubAutoConfiguration;
import com.microsoft.azure.eventhubs.spring.EventHubTemplate;
import com.microsoft.azure.server.pixeltracker.api.handlers.Handler;
import com.microsoft.azure.server.pixeltracker.api.handlers.impl.EventHubSendHandler;
import com.microsoft.azure.server.pixeltracker.api.handlers.impl.JsonQueryStringHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * handlers bean configuration
     * <p>
     * First, convert from query string to json.
     * Last, send async to event hub.
     *
     * @return list of configured handlers
     */
    @Bean
    Handler handlers(EventHubAutoConfiguration ehConfig) throws Exception {
        return jsonQueryStringHandler
                .setNextOperation(eventHubSendHandler(ehConfig));
    }

    private final JsonQueryStringHandler jsonQueryStringHandler = new JsonQueryStringHandler();

    @Bean
    Handler eventHubSendHandler(EventHubAutoConfiguration ehConfig) {
        return new EventHubSendHandler(ehConfig);
    }

    @Bean
    EventHubAutoConfiguration eventHubAutoConfiguration(EventHubTemplate ehTemplate) {
        return new EventHubAutoConfiguration(ehTemplate);
    }

    @Bean
    EventHubTemplate eventHubClientProperties(
            @Value(value = "#{environment.EventHubServiceNamespace}") String serviceBusNamespaceName,
            @Value("#{environment.EventHub}") String eventHubName,
            @Value("#{environment.EventHubServicePolicy}") String sharedAccessSignatureKeyName,
            @Value("#{environment.EventHubServiceKey}") String sharedAccessSignatureKey) throws Exception {
        return new EventHubTemplate(eventHubName, serviceBusNamespaceName, sharedAccessSignatureKeyName, sharedAccessSignatureKey);
    }
}
