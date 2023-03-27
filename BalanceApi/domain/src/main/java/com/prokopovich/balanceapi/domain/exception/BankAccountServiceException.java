package com.prokopovich.balanceapi.domain.exception;

public class BankAccountServiceException extends RuntimeException {

    private int statusCode;

    public BankAccountServiceException(String reason) {
        super(reason);
    }

    public BankAccountServiceException(String reason, Throwable cause) {
        super(reason, cause);
    }

    public BankAccountServiceException(String reason, int statusCode) {
        super(reason);
        this.statusCode = statusCode;
    }

    public BankAccountServiceException(String reason, Throwable cause, int statusCode) {
        super(reason, cause);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}