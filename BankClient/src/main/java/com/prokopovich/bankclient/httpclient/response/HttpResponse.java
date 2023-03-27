package com.prokopovich.bankclient.httpclient.response;

import lombok.*;

import java.nio.charset.StandardCharsets;

@Data
@AllArgsConstructor
@Builder
public class HttpResponse {

    private int statusCode;

    private byte[] body;

    @Override
    public String toString() {
        return "HttpResponse: " +
                "statusCode = " + statusCode +
                ", body = " + new String(body) + ';';
    }

    public String bodyToString() {

        return new String(body, StandardCharsets.UTF_8);
    }
}
