package com.train.ticket.model;

public class Response {
    private int status;
    private String errorMessage;

    public Response(int status, String message) {
        this.status = status;
        this.errorMessage = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

