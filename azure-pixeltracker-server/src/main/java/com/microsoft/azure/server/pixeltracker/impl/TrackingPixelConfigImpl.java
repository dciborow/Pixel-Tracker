package com.microsoft.azure.server.pixeltracker.impl;

import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.server.pixeltracker.TrackingPixelConfig;
import com.microsoft.azure.server.pixeltracker.api.handlers.TrackingPixelHandler;
import com.microsoft.azure.server.pixeltracker.api.handlers.impl.EventHubSendHandler;
import com.microsoft.azure.server.pixeltracker.api.handlers.impl.JsonQueryStringHandler;
import com.microsoft.azure.server.pixeltracker.api.impl.TrackingPixelApiControllerImpl;
import com.microsoft.azure.servicebus.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.ServiceBusException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;

/**
 * Spring Configuration for Tracking Pixel Server
 * <p>
 * Created by dcibo on 5/25/2017.
 */
@ComponentScan(basePackageClasses = {TrackingPixelApiControllerImpl.class})
@Configuration
@EnableWebMvc
public class TrackingPixelConfigImpl implements TrackingPixelConfig {

    @Bean
    public String namespaceName() {
        return System.getenv("ServiceBusNamespaceName");
    }

    @Bean
    public String eventHubName() {
        return System.getenv("EventHubName");
    }

    @Bean
    public String sasKeyName() {
        return System.getenv("SharedAccessSignatureKeyName");
    }

    @Bean
    public String sasKey() {
        return System.getenv("SharedAccessSignatureKey");
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
    public com.microsoft.azure.server.pixeltracker.api.handlers.impl.MockTrackingPixelHandlerImpl handlers() throws Exception {
        return jsonQueryStringHandler();
    }

    @Bean
    public TrackingPixelHandler jsonQueryStringHandler() {
        return new JsonQueryStringHandler(eventHubSendHandler());
    }

    @Bean
    public TrackingPixelHandler eventHubSendHandler() {
        return new EventHubSendHandler(ehClient());
    }

    @Bean
//    @Scope("singleton")
    public EventHubClient ehClient() {
        ConnectionStringBuilder connStr = new ConnectionStringBuilder(namespaceName(), eventHubName(), sasKeyName(), sasKey());
        try {
            return EventHubClient.createFromConnectionStringSync(connStr.toString());
        } catch (ServiceBusException | IOException e) {
            e.printStackTrace();
        }
        return null;
//        return EventHubClient.createFromConnectionStringSync(System.getenv("eh_connnection_string"));
    }
}
