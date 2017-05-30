package com.microsoft.azure.server.pixeltracker.api.model.impl;

import com.microsoft.azure.server.pixeltracker.api.model.PixelHandlerRequest;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dcibo on 5/25/2017.
 */
public class MockPixelHandlerRequestImpl implements PixelHandlerRequest {
    private HttpServletRequest mockWebRequest;

    public MockPixelHandlerRequestImpl(HttpServletRequest mockWebRequest) {
        this.mockWebRequest = mockWebRequest;
    }

    @Override
    public HttpServletRequest getRequest() {
        return null;
    }

    @Override
    public JSONObject getJson() {
        return null;
    }

    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public PixelHandlerRequest setSuccess(boolean success) {
        return null;
    }

    @Override
    public String getQueryString() {
        return this.mockWebRequest.getQueryString();
    }
}
