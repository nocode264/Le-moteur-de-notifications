package com.formation.notifications.sender;

public interface NotificationSender {
    void sendNotification(String message, String recipient);
    String channel();
}
