package com.nasa.api.response;

import com.google.gson.annotations.SerializedName;

public abstract class Response {

    @SerializedName("status_code")
    private final int statusCode;
    private final String message;

    public Response(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

}