package com.telosa.urlshortener.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UrlService {

    private final Map<String, String> urlMap = new HashMap<>();

    public String shortenUrl(String originalUrl) {
        String code = UUID.randomUUID().toString().substring(0, 6);
        urlMap.put(code, originalUrl);
        return code;
    }

    public String getOriginalUrl(String code) {
        return urlMap.getOrDefault(code, "https://google.com"); // fallback
    }
}
