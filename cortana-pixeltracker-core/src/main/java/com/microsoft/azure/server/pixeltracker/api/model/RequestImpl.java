package com.microsoft.azure.server.pixeltracker.api.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Pixel Handler Request Model Impl
 *
 * Created by dcibo on 5/25/2017.
 */
public class RequestImpl implements Request {

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
            e.printStackTrace();
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
