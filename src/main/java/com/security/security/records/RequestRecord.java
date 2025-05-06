package com.security.security.records;

public class RequestRecord {
    public record RegisterRecord(
            String name,
            String email,
            String phoneNo
    ){}
}
