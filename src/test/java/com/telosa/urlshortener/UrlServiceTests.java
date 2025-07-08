package com.telosa.urlshortener;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.telosa.urlshortener.service.UrlService;

@SpringBootTest
public class UrlServiceTests {

    @Autowired
    private UrlService urlService;

    @Test
    void testShortenAndRetrieveUrl() {
        String originalUrl = "https://www.example.com";
        String shortCode = urlService.shortenUrl(originalUrl);

        assertNotNull(shortCode);
        assertEquals(originalUrl, urlService.getOriginalUrl(shortCode));
    }
}
