package com.easy.travel.notification.controller;

import com.easy.travel.notification.domain.Notification;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
public class NotificationController {

    Logger log = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    @Qualifier("loadBalance")
    private RestTemplate restTemplate;

    @Value("${travel360.userprofile.url}")
    private String user_profile_url;

    @Autowired
    NotificationController controller;

    @Autowired
    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    @Autowired
    private SendNotificationService service;

    private ScheduledFuture scheduledFuture;

    @RequestMapping(value = "/notification", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
    public ResponseEntity<String> sendNotifications(@RequestBody Notification notification) {
        service.sendMessage(notification);
        return ResponseEntity.ok("success");
    }

    @RequestMapping(value = "/startnotify" , method = RequestMethod.POST)
    public ResponseEntity<String> startNotification(@RequestParam("postTo") String postTo, @RequestParam("username") String username) {
        if(scheduledFuture == null || scheduledFuture.isCancelled()) {
            scheduledFuture = scheduledThreadPoolExecutor.scheduleAtFixedRate(new NotificationHandler(restTemplate, postTo, user_profile_url, controller, username), 5, 15, TimeUnit.SECONDS);
        }
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/stopnotify" , method = RequestMethod.GET)
    public ResponseEntity<String> stopNotification() {
        if(scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
        return ResponseEntity.ok().build();
    }
}
