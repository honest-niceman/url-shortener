package com.example.urlshortener.services;

import com.example.urlshortener.entities.Url;
import com.example.urlshortener.repositories.UrlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShortenerService {
    private final Logger logger = LoggerFactory.getLogger(ShortenerService.class);

    private final UrlRepository urlRepository;

    public ShortenerService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String getShortUrl(String originalUrl) {
        Optional<Url> optionalUrl = urlRepository.findByOriginalUrl(originalUrl);

        if (optionalUrl.isPresent()) {
            return optionalUrl.get()
                    .getShortUrl();
        }

        Url url = new Url();
        url.setShortUrl(shortenUrl(originalUrl));
        url.setOriginalUrl(originalUrl);

        logger.info("The resulted URL object:\n" + url);

        return urlRepository.save(url)
                .getShortUrl();
    }

    public String getLongUrl(String shortUrl) {
        Optional<Url> optionalUrl = urlRepository.findByShortUrl(shortUrl);

        if (optionalUrl.isEmpty()) {
            logger.info("The url corresponding to:%s was not found.".formatted(shortUrl));
            throw new RuntimeException("The url corresponding to:%s was not found.".formatted(shortUrl));
        }

        return optionalUrl.get()
                .getOriginalUrl();
    }

    public String shortenUrl(String originalUrl) {
        String s = String.valueOf(originalUrl.hashCode());
        logger.info("Short link:%s".formatted(s));
        return s;
    }
}
