package com.nasa.api.response;

import com.google.gson.annotations.SerializedName;

public class ErrorResponse {
    
    @SerializedName("status_code")
    private final int statusCode;
    private final String error;
    private final String message;

    public ErrorResponse(ResponseType responseType, String message) {
        this.statusCode = responseType.getStatusCode();
        this.error = responseType.toString();
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public static ErrorResponse notFound(String message) {
        return new ErrorResponse(ResponseType.NOT_FOUND, message);
    }

    public static ErrorResponse internalError(String message) {
        return new ErrorResponse(ResponseType.INTERNAL_ERROR, message);
    }
    
}