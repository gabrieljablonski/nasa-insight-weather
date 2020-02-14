package com.nasa.api.response;


public enum ResponseType {
    OK(200),
    NOT_FOUND(404),
    INTERNAL_ERROR(500);

    private final int statusCode;

    private ResponseType(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        String[] tokens = this.name().toLowerCase().split("_");

        for (int i=0; i<tokens.length; i++) {
            tokens[i] = tokens[i].substring(0, 1).toUpperCase() + tokens[i].substring(1);
        }

        return String.join("", tokens);
    }

    public int getStatusCode() {
        return statusCode;
    }

}