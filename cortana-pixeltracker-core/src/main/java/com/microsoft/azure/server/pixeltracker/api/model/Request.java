/*
 * Copyright (c) Microsoft. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package com.microsoft.azure.server.pixeltracker.api.model;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public interface Request {
    JSONObject getJson();

    boolean isSuccess();

    Request setSuccess(boolean success);

    String getQueryString();

    void put(String key, String value);

    byte[] getBytes(String encoding) throws UnsupportedEncodingException;
}
