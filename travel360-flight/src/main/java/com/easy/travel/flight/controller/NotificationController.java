package com.easy.travel.flight.controller;

import com.easy.travel.flight.domain.Notification;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@RestController
public class NotificationController {
    private DateTimeFormatter sdf = DateTimeFormatter.ofPattern("MM-dd-yyyy'T'HH:mm");

    @Value("${travel360.notification.url}")
    private String notification_url;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(path = "/flight/notify", method = RequestMethod.POST)
    public ResponseEntity<String> callNotification(@RequestBody Notification notification, @RequestParam("username") String username) throws IOException, ParseException {
        System.setProperty("proxyHost", "proxy.cognizant.com");
        System.setProperty("proxyPort", "6050");
        String notificationBody = notification.body.body;
        if(notificationBody.indexOf("#") != -1) {
            String[] data = notificationBody.split("#");
            System.out.println(">>>>>>>>>>>>>>>> " + data[1]);
            long hoursBetween = Math.abs(LocalDateTime.now().until(LocalDateTime.parse(data[1], sdf), ChronoUnit.HOURS));
            data[0] = data[0].replaceFirst("%s", String.valueOf(hoursBetween));
            notification.body.body = data[0];
        }
        System.out.println(new ObjectMapper().writeValueAsString(notification));
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json");
        HttpEntity<Notification> requestEntity = new HttpEntity<>(notification, headers);

        restTemplate.postForEntity(notification_url + "/send/startnotify?postTo=" + notification.postTo + "&username=" + username, "", String.class);

        restTemplate.postForObject(notification_url + "/send/notification", requestEntity, String.class);
        return ResponseEntity.ok("success");
    }
}
