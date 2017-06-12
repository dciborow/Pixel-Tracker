package com.microsoft.azure.pixeltracker.server;

import com.microsoft.azure.eventhubs.EventHubRuntimeInformation;
import com.microsoft.azure.eventhubs.spring.EventHubAutoConfiguration;
import com.microsoft.azure.servicebus.ServiceBusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by dcibo on 6/2/2017.
 */
@RestController
public class IndexController implements Index {

    private final EventHubAutoConfiguration ehClient;

    @Autowired
    public IndexController(EventHubAutoConfiguration ehClient) {
        assert ehClient != null;
        this.ehClient = ehClient;
    }

    @Override
    public String index() {
        boolean eventHubConfig = false;
        try {
            CompletableFuture<EventHubRuntimeInformation> runtimeInformation = this.ehClient.eventHubClient().getRuntimeInformation();
            EventHubRuntimeInformation eventHubRuntimeInformation = runtimeInformation.get();
            eventHubConfig = true;
        } catch (Exception e) {
            System.out.println("e = " + e);
        }

        return "Event Hub Connection Status:" + eventHubConfig + "\n" +
                System.getenv().toString() + "\n" +
                "Greetings from Spring Boot!";
    }
}
