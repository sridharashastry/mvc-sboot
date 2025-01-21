package com.bring.labs.interfaces;

import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class UUIDGeneratorImpl implements UUIDGenerator {
    @Override
    public String generateUUID() {
        return UUID.randomUUID().toString();
    }
}