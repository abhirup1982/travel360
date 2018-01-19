package com.easy.travel.flight.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Notification {
    @JsonProperty("notification")
    public NotificationBody body;

    @JsonProperty("to")
    public String postTo;

    @JsonProperty("priority")
    public String priority = "high";

    public static class NotificationBody{
        public String title;
        public String body;
    }

    public static Notification buildNotification(String postTo, String title, String body) {
        Notification notification = new Notification();
        NotificationBody notificationBody = new NotificationBody();
        notificationBody.title = title;
        notificationBody.body = body;
        notification.body = notificationBody;
        notification.postTo = postTo;
        return notification;
    }
}
