# URL Shortener

This repository contains an application that helps you to make URLs look short and pretty.

## How to use

### Make URL short

1. Open Postman.
2. Create the following `POST` request: `http://localhost:8080/api/v1/url/make-short/{yourURL}`.
   E.g.: `http://localhost:8080/api/v1/url/make-short/stackoverflow.com`
3. The returned value is your short URL.

### Make URL long

1. Open Postman.
2. Create the following `GET` request: `http://localhost:8080/api/v1/url/make-long/{yourShortURL}`. For the example
   above: `http://localhost:8080/api/v1/url/make-long/1608352797`
3. The returned value is your original URL.

### Open short URL

1. Open browser.
2. Follow the URL `http://localhost:8080/api/v1/url/open-short/{yourShortURL}`. For the example
   above: `http://localhost:8080/api/v1/url/open-short/1608352797`
3. The website that belongs to the original URL is opened.

## How to test

* To run **unit tests** go to: `com.example.urlshortener.unit.services.ShortenerServiceTest`
* To run **integration tests** go to: `com.example.urlshortener.integration.services.ShortenerServiceTests`
* To run **web mvc tests** go to: `com.example.urlshortener.mvc.controllers.UrlControllerTests`