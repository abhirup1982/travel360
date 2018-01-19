package com.easy.travel.notification.controller;

import com.easy.travel.notification.domain.Notification;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Service
public class SendNotificationService {

    Logger log = LoggerFactory.getLogger(SendNotificationService.class);

    @Autowired
    @Qualifier("fireBase")
    private RestTemplate restTemplate;

    @Value("${firebase.server.key}")
    private String FIREBASE_SERVER_KEY;

    @Value("${firebase.api.url}")
    private String FIREBASE_API_URL;

    public void sendMessage(final Notification notification) {
        try{
            System.setProperty("proxyHost", "proxy.cognizant.com");
            System.setProperty("proxyPort", "6050");
            ObjectMapper mapper = new ObjectMapper();
            HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(notification));

            ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
            interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
            interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
            interceptors.add(new HeaderRequestInterceptor("Sender-Id", "151521023547"));
            restTemplate.setInterceptors(interceptors);

            String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);

            CompletableFuture<String> pushNotification = CompletableFuture.completedFuture(firebaseResponse);
            CompletableFuture.allOf(pushNotification).join();
            String firebaseRsp = pushNotification.get();
            log.debug(firebaseRsp);
        } catch(Exception e) {
            log.error("Error while sending notification", e);
        }
    }
}
