package com.microsoft.azure.server.pixeltracker.api.model;

import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Model used to base Pixel Handler Request from Handler to Handler
 *
 * Created by dcibo on 5/25/2017.
 */
public interface PixelHandlerRequest {
    HttpServletRequest getRequest();

    JSONObject getJson();

    boolean isSuccess();
    PixelHandlerRequest setSuccess(boolean success);
}
