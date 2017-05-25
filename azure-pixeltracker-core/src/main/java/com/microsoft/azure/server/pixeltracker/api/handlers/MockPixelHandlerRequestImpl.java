package com.microsoft.azure.server.pixeltracker.api.handlers;

import com.microsoft.azure.server.pixeltracker.api.model.PixelHandlerRequest;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dcibo on 5/25/2017.
 */
public class MockPixelHandlerRequestImpl implements PixelHandlerRequest {
    public MockPixelHandlerRequestImpl(HttpServletRequest mockWebRequest) {
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
}
