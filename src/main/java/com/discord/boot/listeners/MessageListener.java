package com.discord.boot.listeners;
import discord4j.core.object.entity.Message;
import reactor.core.publisher.Mono;

public abstract  class MessageListener {
    private String author = "UNKNOWN";
    public Mono<Void> processMessageOC(final Message eventMessage){
        return montarMensagem(eventMessage,String.format("alo '%s'",author));

    }
    public Mono<Void> processMessageBolsonaro(final Message eventMessage){
        return montarMensagem(eventMessage,"hello Bolsonaro");

    }


    private Mono<Void> montarMensagem(Message eventMessage,String mesagem){
        return Mono.just(eventMessage).filter(message -> {
            final Boolean isNotBot = message.getAuthor()
                    .map(user -> !user.isBot())
                    .orElse(false);

            if(isNotBot){
                message.getAuthor().ifPresent(user ->author = user.getUsername());
            }
            return isNotBot;

        }).flatMap(Message::getChannel).flatMap(channel -> channel.createMessage(String.format(mesagem))).then();


    }
}
