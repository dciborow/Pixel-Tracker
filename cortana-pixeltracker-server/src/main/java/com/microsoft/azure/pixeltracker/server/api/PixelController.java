package com.microsoft.azure.pixeltracker.server.api;

import com.microsoft.azure.server.pixeltracker.api.AbstractPixel;
import com.microsoft.azure.server.pixeltracker.api.handlers.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dcibo on 6/2/2017.
 */
@RestController
public class PixelController extends AbstractPixel {
    @Autowired
    public PixelController(Handler handlers) throws Exception {
        super(handlers);
    }
}
