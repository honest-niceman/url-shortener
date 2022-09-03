package com.example.urlshortener.controllers;

import com.example.urlshortener.services.ShortenerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/url")
public class UrlController {
    private final Logger logger = LoggerFactory.getLogger(UrlController.class);

    private final ShortenerService shortenerService;

    public UrlController(ShortenerService shortenerService) {
        this.shortenerService = shortenerService;
    }

    @PostMapping("/make-short/{url}")
    public String makeUrlShort(@PathVariable @NonNull String url) {
        logger.info("Received URL to make it short:%s".formatted(url));
        return shortenerService.getShortUrl(url);
    }

    @GetMapping("/make-long/{url}")
    public String makeUrlLong(@PathVariable @NonNull String url) {
        logger.info("Received URL to make it long:%s".formatted(url));
        return shortenerService.getLongUrl(url);
    }

    @RequestMapping(value = "/open-short/{url}", method = RequestMethod.GET)
    public ResponseEntity<Void> redirect(@PathVariable @NonNull String url) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("https://%s".formatted(shortenerService.getLongUrl(url))))
                .build();
    }
}