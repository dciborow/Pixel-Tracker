/*
 * Copyright (c) Microsoft. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package com.microsoft.azure.server.pixeltracker.api.handlers.impl;

import com.microsoft.azure.server.pixeltracker.api.handlers.Handler;
import com.microsoft.azure.server.pixeltracker.api.model.Request;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.concurrent.Future;

public abstract class AbstractHandler implements Handler {
    private Handler childHandler = null;

    @Override
    public Handler setNextOperation(Handler childHandler) {
        return setChildHandler(childHandler);
    }

    @Override
    public Future<Boolean> handle(Request request) throws Exception {
        strategy(request);
        return chainHandler(request);
    }

    private Handler setChildHandler(Handler childHandler) {
        if (this.childHandler == null)
            this.childHandler = childHandler;
        else
            this.childHandler.setNextOperation(childHandler);
        return this;
    }

    private Future<Boolean> chainHandler(Request request) throws Exception {
        return childHandler == null
                ? new AsyncResult<>(request.isSuccess())
                : this.childHandler.handle(request);
    }
}
