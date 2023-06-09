package com.prokopovich.bankclient.httpclient.exception;

public class HttpClientException extends RuntimeException {

    public HttpClientException(String reason) {
        super("HTTP error: " + reason);
    }

    public HttpClientException(String reason, Throwable cause) {
        super("HTTP error: " + reason, cause);
    }
}
