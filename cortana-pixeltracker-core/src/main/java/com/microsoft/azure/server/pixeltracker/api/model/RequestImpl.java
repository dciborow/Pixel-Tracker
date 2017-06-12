/*
 * Copyright (c) Microsoft. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package com.microsoft.azure.server.pixeltracker.api.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class RequestImpl implements Request {
    private static Logger logger = LogManager.getLogger();
    private final JSONObject json = new JSONObject();
    private boolean success = true;
    private final Map<String, String> queryParameters;

    public RequestImpl(Map<String, String> queryParameters) {
        this.queryParameters = queryParameters;
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public Request setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    @Override
    public String getQueryString() {
        return queryParameters.toString();
    }

    @Override
    public void put(String key, String value) {
        try {
            getJson().put(key, value);
        } catch (JSONException e) {
            logger.error(e);
        }
    }

    @Override
    public byte[] getBytes(String encoding) throws UnsupportedEncodingException {
        return getJson().toString().getBytes(encoding);
    }

    @Override
    public JSONObject getJson() {
        return json;
    }
}
