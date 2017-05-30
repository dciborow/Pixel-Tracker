package com.microsoft.azure.server.pixeltracker.api.model.impl;

import com.microsoft.azure.server.pixeltracker.api.model.PixelHandlerRequest;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Pixel Handler Request Model Impl
 *
 * Created by dcibo on 5/25/2017.
 */
public class PixelHandlerRequestImpl implements PixelHandlerRequest {

    private final HttpServletRequest request;
    private final JSONObject json = new JSONObject();
    private boolean success = true;

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public PixelHandlerRequest setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    @Override
    public String getQueryString() {
        return getRequest().getQueryString();
    }

    @Override
    public void put(String key, String value) {
        getJson().put(key, value);
    }

    @Override
    public byte[] getBytes(String encoding) throws UnsupportedEncodingException {
        return getJson().toString().getBytes(encoding);
    }

    public PixelHandlerRequestImpl(HttpServletRequest request){
        this.request = request;
    }

    @Override
    public HttpServletRequest getRequest() {
        return request;
    }

    @Override
    public JSONObject getJson() {
        return json;
    }
}
