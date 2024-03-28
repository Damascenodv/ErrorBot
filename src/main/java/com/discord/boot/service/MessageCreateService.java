package com.discord.boot.service;

import com.discord.boot.comandos.CommandosGenericos;
import com.discord.boot.listeners.EventListner;
import com.discord.boot.listeners.MessageListener;
import discord4j.core.event.domain.message.MessageCreateEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MessageCreateService extends MessageListener implements EventListner<MessageCreateEvent> {
    @Override
    public Class<MessageCreateEvent> getEventType() {
        return MessageCreateEvent.class;
    }

    @Override
    public Mono<Void> execute(final MessageCreateEvent event){
            if(event.getMessage().getContent().equals(CommandosGenericos.hello)){
             return processMessage(event.getMessage());
            }
            return Mono.empty();


    }
}
