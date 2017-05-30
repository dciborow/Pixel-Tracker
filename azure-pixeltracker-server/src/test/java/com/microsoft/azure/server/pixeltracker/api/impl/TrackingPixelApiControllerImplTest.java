package com.microsoft.azure.server.pixeltracker.api.impl;

import com.microsoft.azure.server.pixeltracker.TestTrackingPixelConfigImpl;
import com.microsoft.azure.server.pixeltracker.TestWebRequest;
import com.microsoft.azure.server.pixeltracker.api.handlers.HandlerList;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit5 tests for Tracking Pixel API Controller Impl
 * <p>
 * Created by dcibo on 5/25/2017.
 */
class TrackingPixelApiControllerImplTest {

    @Test
    void get() throws Exception {
        HandlerList handlers = new TestTrackingPixelConfigImpl().handlers();
        TestWebRequest testWebRequest = new TestWebRequest();
        testWebRequest.setQueryString("test1=1&test2=2");
        ResponseEntity<byte[]> responseEntity = new TrackingPixelApiControllerImpl(handlers)
                .get(testWebRequest);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.ACCEPTED);
    }

}