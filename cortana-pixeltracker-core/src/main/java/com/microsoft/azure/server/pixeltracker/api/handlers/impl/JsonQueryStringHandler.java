package com.microsoft.azure.server.pixeltracker.api.handlers.impl;

import com.microsoft.azure.server.pixeltracker.api.model.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.stream.Stream;

public class JsonQueryStringHandler extends AbstractHandler {
    private static Logger logger = LogManager.getLogger();
    private final String PARAMDELIM = "&";

    @Override
    public void strategy(Request request) throws Exception {
        logger.trace("Json Query String Strategy");
        String queryString = request.getQueryString();
        if (isNotEmpty(queryString))
            getParamsFrom(queryString)
                    .forEach(param -> putIntoOutput(param, request));
        else request.setSuccess(false);
    }

    private boolean isNotEmpty(String queryString) {
        return queryString.length() > 0;
    }

    private Stream<String> getParamsFrom(String queryString) {
        return Arrays.stream(queryString.split(PARAMDELIM));
    }

    private void putIntoOutput(String param, Request request) {
        String[] nameValue = param.split("=");
        if (nameValue.length > 1) request.put(nameValue[0], nameValue[1]);
    }
}
