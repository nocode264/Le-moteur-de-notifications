package com.formation.notifications.exception;

public class UnknownChannelException extends RuntimeException {
    public UnknownChannelException(String channel){
        super("Canal de notification inconnu : '"+channel+"'");
    }
}
