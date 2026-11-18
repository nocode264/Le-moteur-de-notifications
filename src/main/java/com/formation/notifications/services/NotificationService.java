package com.formation.notifications.services;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.formation.notifications.exception.UnknownChannelException;
import com.formation.notifications.sender.NotificationSender;

@Service
public class NotificationService {
    private final Map<String, NotificationSender> sendersByChannel;

    public NotificationService(List<NotificationSender> senders) {
        this.sendersByChannel = senders.stream()
                .collect(Collectors.toUnmodifiableMap(
                        NotificationSender::channel,
                        Function.identity()));
    }

    public void sendToAllChannels(String recipient, String message) {
        sendersByChannel.values().forEach(sender -> sender.sendNotification(message, recipient));
    }

    public void sendViaChannel(String channel, String recipient, String message) {
        NotificationSender sender = sendersByChannel.get(normalize(channel));
        if (sender == null) {
            throw new UnknownChannelException(channel);
        }
        sender.sendNotification(message, recipient);
    }

    private static String normalize(String channel) {
        return channel == null ? "" : channel.trim().toLowerCase(Locale.ROOT);
    }
}
