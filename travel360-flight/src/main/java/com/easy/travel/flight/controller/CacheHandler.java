package com.easy.travel.flight.controller;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class CacheHandler<T> implements Runnable{

    public enum CACHE_KEY {
        FLIGHTS, GEO
    }

    private final CACHE_KEY cacheKey;
    private final RestTemplate template;
    private final T[] requestObj;
    private final String cache_url;


    public CacheHandler(RestTemplate template, CACHE_KEY cacheKey, String cache_url, T... requestObj) {
        this.template = template;
        this.cacheKey = cacheKey;
        this.requestObj = requestObj;
        this.cache_url = cache_url;
    }

    @Override
    public void run() {
        System.setProperty("proxyHost", "proxy.cognizant.com");
        System.setProperty("proxyPort", "6050");
        switch(cacheKey) {
            case FLIGHTS:{
                template.put(cache_url + "/cache/flights/", requestObj[0]);
                break;
            }case GEO:{
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<T> entity = new HttpEntity<T>(requestObj[1], headers);
                template.put(cache_url + "/cache/coordinates/"+requestObj[0], entity);
                break;
            }
        }
    }
}
