package com.example.urlshortener.integration.services;

import com.example.urlshortener.services.ShortenerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ShortenerServiceTests {
    @Autowired
    private ShortenerService shortenerService;

    @Test
    void getShortUrl() {
        Assertions.assertEquals(shortenerService.getShortUrl("stackoverflow.com"), "1608352797");
    }

    @Test
    void getLongUrl() {
        Assertions.assertEquals(shortenerService.getLongUrl("1608352797"), "stackoverflow.com");
    }

    @Test
    void getLongUrlFromInvalidShortUrl() {
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class,
                                                          () -> shortenerService.getLongUrl("1"));

        Assertions.assertEquals("The url corresponding to:1 was not found.", thrown.getMessage());
    }
}
