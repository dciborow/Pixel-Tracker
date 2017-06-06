package com.microsoft.azure.server.pixeltracker.api.model;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Model used to base Pixel Handler Request from Handler to Handler
 *
 * Created by dcibo on 5/25/2017.
 */
public interface Request {
    JSONObject getJson();

    boolean isSuccess();

    Request setSuccess(boolean success);

    String getQueryString();

    void put(String key, String value);

    byte[] getBytes(String encoding) throws UnsupportedEncodingException;
}
