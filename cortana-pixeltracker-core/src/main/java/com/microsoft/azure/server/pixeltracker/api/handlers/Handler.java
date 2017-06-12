/*
 * Copyright (c) Microsoft. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package com.microsoft.azure.server.pixeltracker.api.handlers;

import com.microsoft.azure.server.pixeltracker.api.model.Request;

import java.util.concurrent.Future;

public interface Handler {
    void strategy(Request request) throws Exception;

    Handler setNextOperation(Handler childHandler);

    Future<Boolean> handle(Request request) throws Exception;
}
