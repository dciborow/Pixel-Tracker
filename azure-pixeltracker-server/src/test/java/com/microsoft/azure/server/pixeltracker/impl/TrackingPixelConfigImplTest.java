package com.microsoft.azure.server.pixeltracker.impl;

import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.server.pixeltracker.TestTrackingPixelConfigImpl;
import com.microsoft.azure.server.pixeltracker.api.handlers.HandlerList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test for Spring Config Implementation
 * <p>
 * Created by dcibo on 5/25/2017.
 */
@Configuration
class TrackingPixelConfigImplTest {

    private static AnnotationConfigApplicationContext context;

    @BeforeAll
    static void setUp() {
        context = new AnnotationConfigApplicationContext(TestTrackingPixelConfigImpl.class);
    }

    @Test
    void handlers() {
        HandlerList handlers = context.getBean("handlers", HandlerList.class);
        assertNotNull(handlers);
    }

    @Disabled
    void ehClient() throws Exception{
        EventHubClient ehClient = context.getBean("ehClient", EventHubClient.class);
        assertTrue(ehClient instanceof EventHubClient);
    }
}