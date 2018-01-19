package com.easy.travel.notification.controller;

import com.easy.travel.notification.domain.Notification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class NotificationHandler implements Runnable {

    private RestTemplate restTemplate;

    private String postTo;

    private String user_profile_url;

    private NotificationController controller;

    private String username;

    public NotificationHandler(final RestTemplate restTemplate, String postTo, String userProfileUrl, NotificationController controller, String username) {
        this.restTemplate = restTemplate;
        this.postTo = postTo;
        this.user_profile_url = userProfileUrl;
        this.controller = controller;
        this.username = username;
    }

    @Override
    public void run() {
        System.out.println("******* Send Notifications *****");
        ResponseEntity<Notification> entity = restTemplate.getForEntity(user_profile_url + "/fetch/notifications?username=" + username, Notification.class);
        Notification notification = entity.getBody();
        controller.sendNotifications(Notification.buildNotification(postTo, notification.body.title, notification.body.body));
    }
}
