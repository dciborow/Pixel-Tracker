package com.microsoft.azure.server.pixeltracker.api.handlers;

import com.microsoft.azure.server.pixeltracker.api.model.Request;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Future;

/**
 * Tracking Pixel Handler Interface
 * <p>
 * Created by dcibo on 5/24/2017.
 */
@EnableAsync
public interface Handler {
    void strategy(Request request) throws Exception;
    Handler setNextOperation(Handler childHandler);

    @Async
    Future<Boolean> handle(Request request) throws Exception;
}
