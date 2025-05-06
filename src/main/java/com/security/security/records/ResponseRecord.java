package com.security.security.records;

import org.springframework.http.HttpStatus;

public class ResponseRecord {

    public record Response(
            HttpStatus status,
            int code,
            String message,
            Object data
    ) {}

}
