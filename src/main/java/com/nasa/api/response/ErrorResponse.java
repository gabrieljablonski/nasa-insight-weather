package com.nasa.api.response;

public class ErrorResponse extends Response {
    
    private final String error;

    public ErrorResponse(ResponseType responseType, String message) {
        super(responseType.getStatusCode(), message);
        this.error = responseType.toString();
    }

    public String getError() {
        return error;
    }

    public static ErrorResponse notFound(String message) {
        return new ErrorResponse(ResponseType.NOT_FOUND, message);
    }

    public static ErrorResponse internalError(String message) {
        return new ErrorResponse(ResponseType.INTERNAL_ERROR, message);
    }
    
}
