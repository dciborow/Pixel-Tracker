package com.microsoft.azure.server.pixeltracker.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by dcibo on 6/2/2017.
 */
public interface Pixel {

    @RequestMapping(value = "/pixel",
            produces = MediaType.IMAGE_GIF_VALUE,
            method = RequestMethod.GET, headers = "Accept=*/*")
    byte[] pixel(@RequestParam Map<String, String> queryParameters);
}
