package com.microsoft.azure.server.pixeltracker.impl;

import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.server.pixeltracker.TestTrackingPixelConfigImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test for Spring Config Implementation
 * <p>
 * Created by dcibo on 5/25/2017.
 */
class TrackingPixelConfigImplTest {

    private static AnnotationConfigApplicationContext context;

    @BeforeAll
    static void setUp() {
        context = new AnnotationConfigApplicationContext(TestTrackingPixelConfigImpl.class);
    }

    @Test
    void handlers() {
        ArrayList handlers = context.getBean("handlers", ArrayList.class);
        assertNotNull(handlers);
    }

    @Disabled
    void ehClient() throws Exception{
        EventHubClient ehClient = context.getBean("ehClient", EventHubClient.class);
        assertTrue(ehClient instanceof EventHubClient);
    }
}