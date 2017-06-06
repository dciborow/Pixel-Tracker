package com.microsoft.azure.server.pixeltracker.api.handlers.impl;

import com.microsoft.azure.server.pixeltracker.api.handlers.Handler;
import com.microsoft.azure.server.pixeltracker.api.model.Request;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.concurrent.Future;

/**
 * Created by dcibo on 6/2/2017.
 */
public abstract class AbstractHandler implements Handler {
    private Handler childHandler = null;

    @Override
    public Handler setNextOperation(Handler childHandler) {
        if (this.childHandler == null)
            this.childHandler = childHandler;
        else
            this.childHandler.setNextOperation(childHandler);
        return this;
    }

    @Override
    public Future<Boolean> handle(Request request) throws Exception {
        strategy(request);
        return childHandler != null ?
                this.childHandler.handle(request) : new AsyncResult<>(request.isSuccess());
    }
}
