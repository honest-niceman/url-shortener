package com.example.urlshortener.unit.services;

import com.example.urlshortener.entities.Url;
import com.example.urlshortener.repositories.UrlRepository;
import com.example.urlshortener.services.ShortenerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
class ShortenerServiceTest {
    @Autowired
    private ShortenerService shortenerService;
    @MockBean
    private UrlRepository urlRepository;

    @Test
    void getShortUrlExists() {
        // given
        String url = "stackoverflow.com";
        String shortUrl = "1608352797";

        Url urlObj = new Url();
        urlObj.setShortUrl(shortUrl);
        urlObj.setOriginalUrl(url);

        // when
        Mockito.when(urlRepository.findByOriginalUrl(url))
                .thenReturn(Optional.of(urlObj));

        // then
        String receivedUrl = shortenerService.getShortUrl(url);
        Assertions.assertEquals(shortUrl, receivedUrl);
    }

    @Test
    void getShortUrlNotExists() {
        // given
        String url = "stackoverflow.com";
        String shortUrl = "1608352797";

        Url urlObj = new Url();
        urlObj.setShortUrl(shortUrl);
        urlObj.setOriginalUrl(url);

        // when
        Mockito.when(urlRepository.findByShortUrl(url))
                .thenReturn(Optional.empty());
        Mockito.when(urlRepository.save(Mockito.any(Url.class)))
                .thenReturn(urlObj);

        // then
        String receivedUrl = shortenerService.getShortUrl(url);
        Assertions.assertEquals(shortUrl, receivedUrl);
    }

    @Test
    void getLongUrlExists() {
        // given
        String url = "stackoverflow.com";
        String shortUrl = "1608352797";

        Url urlObj = new Url();
        urlObj.setShortUrl(shortUrl);
        urlObj.setOriginalUrl(url);

        // when
        Mockito.when(urlRepository.findByShortUrl(shortUrl))
                .thenReturn(Optional.of(urlObj));

        // then
        Assertions.assertEquals("stackoverflow.com", shortenerService.getLongUrl(shortUrl));
    }

    @Test
    void getLongUrlNotExists() {
        // given
        String shortUrl = "1";

        // when
        Mockito.when(urlRepository.findByShortUrl(shortUrl))
                .thenReturn(Optional.empty());

        // then
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class,
                                                          () -> shortenerService.getLongUrl("1"));

        Assertions.assertEquals("The url corresponding to:1 was not found.", thrown.getMessage());
    }

    @Test
    void getShortenUrl() {
        // given
        String url = "stackoverflow.com";

        // when
        String shortenUrl = shortenerService.shortenUrl(url);

        // then
        Assertions.assertEquals("1608352797", shortenUrl);
    }
}