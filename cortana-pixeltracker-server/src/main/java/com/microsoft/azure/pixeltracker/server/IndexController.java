package com.microsoft.azure.pixeltracker.server;

import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dcibo on 6/2/2017.
 */
@RestController
public class IndexController implements Index {

    @Override
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
