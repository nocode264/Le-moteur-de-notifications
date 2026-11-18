package com.formation.notifications.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.formation.notifications.exception.UnknownChannelException;
import com.formation.notifications.services.NotificationService;

@Component 
public class NotificationDemoRunner implements CommandLineRunner{
    private static final Logger log = LoggerFactory.getLogger(NotificationDemoRunner.class);
    private final NotificationService notificationService;
    public NotificationDemoRunner(NotificationService notificationService){
        this.notificationService = notificationService;
    }
    @Override
    public void run(String... args){
        log.info("---1. envoi sur tous les canaux ---");
        notificationService.sendToAllChannels("toto@gmail.com", "hello");
        log.info("---2. envoi sur un canal precis ---");
        notificationService.sendViaChannel("sms","+237 699999999", "hello world !");
        log.info("---3. envoi sur un canal inexistant ---");
        try{
            notificationService.sendViaChannel("whatsapp", "+237 699009900", "envoi via whatsapp");
        }catch(UnknownChannelException e){
            log.warn("envoi refuse : {} ", e.getMessage());
        }
    }
}
