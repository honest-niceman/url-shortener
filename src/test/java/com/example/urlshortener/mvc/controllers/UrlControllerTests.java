package com.example.urlshortener.mvc.controllers;

import com.example.urlshortener.services.ShortenerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UrlControllerTests.class)
@ComponentScan(basePackages = "com.example")
public class UrlControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ShortenerService shortenerService;

    @Test
    void createShortUrlTest() throws Exception {
        Mockito.when(shortenerService.getShortUrl("stackoverflow.com"))
                .thenReturn("1608352797");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post(
                "/api/v1/url/make-short/stackoverflow.com");

        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status()
                                   .isOk())
                .andExpect(MockMvcResultMatchers.content()
                                   .string(Matchers.is("1608352797")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getLongUrlTest() throws Exception {
        Mockito.when(shortenerService.getLongUrl("1608352797"))
                .thenReturn("stackoverflow.com");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get(
                "/api/v1/url/make-long/1608352797");

        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status()
                                   .isOk())
                .andExpect(MockMvcResultMatchers.content()
                                   .string(Matchers.is("stackoverflow.com")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void openShortUrlTest() throws Exception {
        Mockito.when(shortenerService.getLongUrl("1608352797"))
                .thenReturn("stackoverflow.com");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get(
                "/api/v1/url/open-short/1608352797");

        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status()
                                   .is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("https://stackoverflow.com"))
                .andDo(MockMvcResultHandlers.print());
    }
}
