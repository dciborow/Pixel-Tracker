package com.microsoft.azure.server.pixeltracker.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface for Tracking Pixel API Controller
 * <p>
 * This is used to ensure that future versions of the API remain consistent
 * <p>
 * <p>
 * Created by dcibo on 5/24/2017.
 */
public interface TrackingPixelApiController {

    /**
     * Tracking Pixel Handler used to print query string
     *
     * @param request client web request
     * @return future boolean declaring completed status
     */
    @ApiOperation(value = "Pixel Tracking API", response = Void.class, tags = {"pixel",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS", response = String.class),
            @ApiResponse(code = 400, message = "BAD_REQUEST", response = String.class),
            @ApiResponse(code = 424, message = "FAILED", response = String.class)})
    @RequestMapping(value = "/pixel.gif",
            produces = MediaType.IMAGE_GIF_VALUE,
            method = RequestMethod.GET, headers = "Accept=*/*")
    ResponseEntity<byte[]> get(HttpServletRequest request);
}
