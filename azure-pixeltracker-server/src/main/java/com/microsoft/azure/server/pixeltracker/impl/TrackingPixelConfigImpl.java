package com.microsoft.azure.server.pixeltracker.impl;

import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.server.pixeltracker.TrackingPixelConfig;
import com.microsoft.azure.server.pixeltracker.api.handlers.TrackingPixelHandler;
import com.microsoft.azure.server.pixeltracker.api.handlers.impl.EventHubSendHandler;
import com.microsoft.azure.server.pixeltracker.api.handlers.impl.JsonQueryStringHandler;
import com.microsoft.azure.servicebus.ConnectionStringBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Spring Configuration for Tracking Pixel Server
 * <p>
 * Created by dcibo on 5/25/2017.
 */
@Configuration
@EnableWebMvc
public class TrackingPixelConfigImpl implements TrackingPixelConfig {
    private final String namespaceName = "----ServiceBusNamespaceName-----";
    private final String eventHubName = "----EventHubName-----";
    private final String sasKeyName = "-----SharedAccessSignatureKeyName-----";
    private final String sasKey = "---SharedAccessSignatureKey----";

    /**
     * handlers bean configuration
     *
     * @return list of configured handlers
     */
    @Bean
    public List<TrackingPixelHandler> handlers() throws Exception {
        return Stream.of(
                new JsonQueryStringHandler(),
                new EventHubSendHandler(ehClient())
        ).collect(Collectors.toList());
    }

    @Bean
//    @Scope("singleton")
    public EventHubClient ehClient() throws Exception {
        ConnectionStringBuilder connStr = new ConnectionStringBuilder(namespaceName, eventHubName, sasKeyName, sasKey);
        return EventHubClient.createFromConnectionStringSync(connStr.toString());
//        return EventHubClient.createFromConnectionStringSync(System.getenv("eh_connnection_string"));
    }
}
