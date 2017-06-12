/*
 * Copyright (c) Microsoft. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package com.microsoft.azure.server.pixeltracker.api;

import com.microsoft.azure.server.pixeltracker.api.handlers.Handler;
import com.microsoft.azure.server.pixeltracker.api.model.RequestImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class AbstractPixel implements Pixel {
    private static Logger logger = LogManager.getLogger();
    private final Handler handlers;

    public AbstractPixel(Handler handlers) throws Exception {
        if (handlers == null) throw new Exception("Handler not expected to be null. Check your Spring Config!");
        this.handlers = handlers;
    }

    @Override
    public byte[] pixel(Map<String, String> queryParameters) {
        try {
            handlers.handle(new RequestImpl(queryParameters));
        } catch (Exception e) {
            logger.error(e);
        }
        return new byte[]{0};
    }
}
