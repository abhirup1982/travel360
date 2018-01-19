package com.easy.travel.flight.controller;

import com.easy.travel.flight.configuration.NotificationBuilder;
import com.easy.travel.flight.domain.Notification;
import com.easy.travel.flight.domain.UserProfile;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public class NotificationHandler implements Runnable {

    private RestTemplate restTemplate;
    private String notification_url;
    private String postTo;
    private UserProfile userProfile;

    public NotificationHandler(final RestTemplate restTemplate, final String notification_url, String postTo, UserProfile userProfile) {
        this.restTemplate = restTemplate;
        this.notification_url = notification_url;
        this.postTo = postTo;
        this.userProfile = userProfile;
    }

    @Override
    public void run() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Notification notification = NotificationBuilder.getNotification(postTo, userProfile);
            HttpHeaders headers = new HttpHeaders();
            headers.add("content-type", "application/json");
            HttpEntity<Notification> requestEntity = new HttpEntity<>(notification, headers);
            System.out.println("******* Send Notification *******");
            //restTemplate.postForObject(notification_url + "/send/notification", requestEntity, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
