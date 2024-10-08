package com.discord.boot.service;

import org.springframework.stereotype.Service;

import com.discord.boot.comandos.CommandosGenericos;
import com.discord.boot.listeners.EventListner;
import com.discord.boot.listeners.MessageListener;

import discord4j.core.event.domain.message.MessageCreateEvent;
import reactor.core.publisher.Mono;

@Service
public class MessageCreateService extends MessageListener implements EventListner<MessageCreateEvent> {
    @Override
    public Class<MessageCreateEvent> getEventType() {
        return MessageCreateEvent.class;
    }

    @Override
    public Mono<Void> execute(final MessageCreateEvent event) {
        String commnadEvent = event.getMessage().getContent();

        if (commnadEvent.equals(CommandosGenericos.hello)) {
            return processMessageOC(event.getMessage());
        }
        if (commnadEvent.equals(CommandosGenericos.bolosnaro)) {
            return processMessageBolsonaro(event.getMessage());
        }
        if (commnadEvent.equals(CommandosGenericos.cadastroUsuario)) {
            return processCadastroUsuario(event.getMessage());
        }
        if (commnadEvent.equals(CommandosGenericos.atualizaUsuario)) {

            return processAtualizaUsuario(event.getMessage());
        }
        if (commnadEvent.contains("Calcular")) {

            return processCalculo(event.getMessage());
        }
        return Mono.empty();

    }
}
