package com.microsoft.azure.pixeltracker.server;

import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.server.pixeltracker.api.handlers.Handler;
import com.microsoft.azure.server.pixeltracker.api.handlers.impl.EventHubSendHandler;
import com.microsoft.azure.server.pixeltracker.api.handlers.impl.JsonQueryStringHandler;
import com.microsoft.azure.servicebus.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.ServiceBusException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

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
    public Handler handlers() throws Exception {
        return jsonQueryStringHandler()
                .setNextOperation(eventHubSendHandler());
    }

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

    @Bean
    public Handler jsonQueryStringHandler() {
        return new JsonQueryStringHandler();
    }

    @Bean
    public Handler eventHubSendHandler() {
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
