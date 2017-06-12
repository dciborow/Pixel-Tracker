/*
 * Copyright (c) Microsoft. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package com.microsoft.azure.server.pixeltracker.api.handlers.impl;

import com.microsoft.azure.server.pixeltracker.api.model.Request;

import java.util.Arrays;

public class JsonQueryStringHandler extends AbstractHandler {

    @Override
    public void strategy(Request request) throws Exception {

        String queryString = request.getQueryString();
        if (queryString.length() <= 0) request.setSuccess(false);
        else {
            Arrays.stream(queryString.split("&"))
                    .forEach(s -> {
                        String[] value = s.split("=");
                        if (value.length > 1) request.put(value[0], value[1]);
                    });
            request.setSuccess(true);
        }
    }
}
