package com.microsoft.azure.server.pixeltracker.impl;

import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.server.pixeltracker.TrackingPixelConfig;
import com.microsoft.azure.server.pixeltracker.api.handlers.TrackingPixelHandler;
import com.microsoft.azure.server.pixeltracker.api.handlers.impl.EventHubTrackingPixelHandler;
import com.microsoft.azure.server.pixeltracker.api.handlers.impl.TrackingPixelHandlerImpl;
import com.microsoft.azure.servicebus.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.ServiceBusException;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Spring Configuration for Tracking Pixel Server
 * <p>
 * Created by dcibo on 5/25/2017.
 */
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
    public final List<TrackingPixelHandler> handlers() throws Exception {
        List<TrackingPixelHandler> handlers = new ArrayList<>();
        handlers.add(new TrackingPixelHandlerImpl());
        handlers.add(new EventHubTrackingPixelHandler(ehClient()));
        return handlers;
    }

    @Bean
    public final EventHubClient ehClient() throws Exception {
        ConnectionStringBuilder connStr = new ConnectionStringBuilder(namespaceName, eventHubName, sasKeyName, sasKey);
        return EventHubClient.createFromConnectionStringSync(connStr.toString());
    }
}
