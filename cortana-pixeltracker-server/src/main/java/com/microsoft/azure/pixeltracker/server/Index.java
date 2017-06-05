package com.microsoft.azure.pixeltracker.server;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dcibo on 6/2/2017.
 */
public interface Index {
    @RequestMapping("/")
    String index();
}
