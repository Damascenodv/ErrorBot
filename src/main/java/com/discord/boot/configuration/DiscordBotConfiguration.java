package com.discord.boot.configuration;


import com.discord.boot.listeners.EventListner;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DiscordBotConfiguration {
    @Value("${token}")
    private String token;

    @Bean
    public <T extends Event>GatewayDiscordClient gatewayDiscordClient (final List<EventListner<T>> eventListnerList){
        final  GatewayDiscordClient client = DiscordClientBuilder.create(token).build().login().block();

        for(final EventListner<T> listner : eventListnerList){
            client.on(listner.getEventType()).flatMap(listner::execute).onErrorResume(listner::handleError).subscribe();
        }
        return client;

    }
}
