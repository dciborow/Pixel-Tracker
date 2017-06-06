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

/**
 * Created by dcibo on 6/2/2017.
 */

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
            @Value(value = "#{environment.ServiceBusNamespaceName}") String serviceBusNamespaceName,
            @Value("#{environment.EventHubName}") String eventHubName,
            @Value("#{environment.sharedAccessSignatureKeyName}") String sharedAccessSignatureKeyName,
            @Value("#{environment.SharedAccessSignatureKey}") String sharedAccessSignatureKey) {
        return new EventHubTemplate()
                .setEventHubName(eventHubName)
                .setServiceBusNamespaceName(serviceBusNamespaceName)
                .setSharedAccessSignatureKey(sharedAccessSignatureKeyName)
                .setSharedAccessSignatureKey(sharedAccessSignatureKey);
    }
}
