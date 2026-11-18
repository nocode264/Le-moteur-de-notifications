package com.formation.notifications.sender;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component 
public class EmailSender implements NotificationSender{
    private static final Logger log = LoggerFactory.getLogger(EmailSender.class);

    @Override 
    public void sendNotification(String message, String recipient) {
        log.info(" [Email] to={} message={}",recipient,message);
    }

    @Override
    public String channel() {
        return "email";
    }

}
