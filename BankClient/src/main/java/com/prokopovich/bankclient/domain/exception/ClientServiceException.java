package com.prokopovich.bankclient.domain.exception;

public class ClientServiceException extends RuntimeException {

    private int statusCode;

    public ClientServiceException(String reason) {
        super("ClientService error: " + reason);
    }

    public ClientServiceException(String reason, int statusCode) {
        super(reason);
        this.statusCode = statusCode;
    }

    public ClientServiceException(String reason, Throwable cause) {
        super("ClientService error: " + reason, cause);
    }
}
