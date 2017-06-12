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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    private static Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        logger.info("Application Start");
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @Autowired
    @Qualifier(value = "enable")
    Handler handlers(Handler... handlers) {
        return chainHandlers(handlers);
    }

    @Bean
    @Qualifier(value = "enable")
    Handler jsonQueryStringHandler() {
        return new JsonQueryStringHandler();
    }

    @Bean
    @Qualifier(value = "enable")
    Handler eventHubSendHandler() throws Exception {
        return new EventHubSendHandler(eventHubAutoConfiguration());
    }

    @Bean
    EventHubAutoConfiguration eventHubAutoConfiguration() throws Exception {
        return new EventHubAutoConfiguration(eventHubClientProperties());
    }

    @Bean
    EventHubTemplate eventHubClientProperties() throws Exception {
        return new EventHubTemplate(eventHubName, serviceBusNamespaceName, sharedAccessSignatureKeyName, sharedAccessSignatureKey);
    }

    @Value(value = "#{environment.EventHubServiceNamespace}")
    String serviceBusNamespaceName;
    @Value("#{environment.EventHub}")
    String eventHubName;
    @Value("#{environment.EventHubServicePolicy}")
    String sharedAccessSignatureKeyName;
    @Value("#{environment.EventHubServiceKey}")
    String sharedAccessSignatureKey;

    private Handler chainHandlers(Handler[] handler) {
        int length = handler.length;

        if (length > 1) {
            for (int i = 1; i < length; i++) {
                handler[0].setNextOperation(handler[1]);
            }
        }
        return handler[0];
    }
}
