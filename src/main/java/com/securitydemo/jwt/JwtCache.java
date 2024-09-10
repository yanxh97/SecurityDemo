package com.securitydemo.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class JwtCache {
    public Map<String, UserDetails> records = new ConcurrentHashMap<>();
}
