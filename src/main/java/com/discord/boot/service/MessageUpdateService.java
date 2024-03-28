package com.discord.boot.service;

import com.discord.boot.listeners.EventListner;
import com.discord.boot.listeners.MessageListener;
import discord4j.core.event.domain.message.MessageUpdateEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MessageUpdateService extends MessageListener implements EventListner<MessageUpdateEvent> {
    @Override
    public Class<MessageUpdateEvent> getEventType() {
        return MessageUpdateEvent.class;
    }

    @Override
    public Mono<Void> execute(MessageUpdateEvent event) {
        return Mono.just(event).filter(MessageUpdateEvent::isContentChanged).flatMap(MessageUpdateEvent::getMessage).flatMap(super::processMessage);
    }
}
