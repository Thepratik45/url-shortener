package controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import service.UrlService;

@Controller
public class UrlController {

    @Autowired
    private UrlService urlService;

    @GetMapping("/")
    public String home() {
        return "form";
    }

    @PostMapping("/shorten")
    public String shortenUrl(@RequestParam("originalUrl") String originalUrl, Model model) {
        String shortCode = urlService.shortenUrl(originalUrl);
        String shortUrl = "http://localhost:8080/short/" + shortCode;
        model.addAttribute("shortUrl", shortUrl);
        return "form";
    }

    @GetMapping("/short/{code}")
    public String redirect(@PathVariable String code) {
        String originalUrl = urlService.getOriginalUrl(code);
        return "redirect:" + originalUrl;
    }
}
