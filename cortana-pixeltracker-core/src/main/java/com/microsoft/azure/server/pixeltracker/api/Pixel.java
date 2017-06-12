/*
 * Copyright (c) Microsoft. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package com.microsoft.azure.server.pixeltracker.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface Pixel {

    @RequestMapping(value = "/pixel",
            produces = MediaType.IMAGE_GIF_VALUE,
            method = RequestMethod.GET, headers = "Accept=*/*")
    byte[] pixel(@RequestParam Map<String, String> queryParameters);
}
