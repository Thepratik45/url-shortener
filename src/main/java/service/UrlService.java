package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Url;
import repository.UrlRepository;

import java.util.Random;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int LENGTH = 6;

    public String shortenUrl(String originalUrl) {
        String shortCode = generateShortCode();
        Url url = new Url(originalUrl, shortCode);
        urlRepository.save(url);
        return shortCode;
    }

    private String generateShortCode() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    public String getOriginalUrl(String shortCode) {
        Url url = urlRepository.findByShortCode(shortCode);
        return url.getOriginalUrl();
    }
}
