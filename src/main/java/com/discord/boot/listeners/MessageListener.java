package com.discord.boot.listeners;
import com.discord.boot.DAO.interfaces.RepositorioJpql;
import com.discord.boot.DAO.SQL.OsCaraSqlDAO;
import com.discord.boot.configuration.DiscordBotConfiguration;
import com.discord.boot.entity.OsCara;
import discord4j.core.object.entity.Message;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public abstract  class MessageListener {
    private String author = "UNKNOWN";
    private  RepositorioJpql<OsCara> repositorio = new OsCaraSqlDAO(DiscordBotConfiguration.dataSource());
    public Mono<Void> processMessageOC(final Message eventMessage){

        return montarMensagem(eventMessage,String.format("alo '%s'",author));

    }
    public Mono<Void> processMessageBolsonaro(final Message eventMessage){
        return montarMensagem(eventMessage,"hello Bolsonaro");

    }

    public Mono<Void> processCadastroUsuario(final Message eventMessage){
        return cadastraPessoa(eventMessage);

    }


    private Mono<Void> montarMensagem(Message eventMessage,String mensagem){
        return Mono.just(eventMessage).filter(message -> {
            final Boolean isNotBot = message.getAuthor()
                    .map(user -> !user.isBot())
                    .orElse(false);

            if(isNotBot){

                message.getAuthor().ifPresent(user ->author = user.getUsername());
                OsCara osCara = new OsCara(eventMessage);
                try {
                    repositorio.insert(osCara);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            return isNotBot;

        }).flatMap(Message::getChannel).flatMap(channel -> channel.createMessage(String.format(mensagem))).then();


    }
    private Mono<Void> cadastraPessoa(Message eventMessage){
        return Mono.just(eventMessage).filter(message -> {
            final Boolean isNotBot = message.getAuthor()
                    .map(user -> !user.isBot())
                    .orElse(false);

            if(isNotBot){

                message.getAuthor().ifPresent(user ->author = user.getUsername());
                OsCara osCara = new OsCara(eventMessage);
                try {
                    repositorio.insert(osCara);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            return isNotBot;

        }).flatMap(Message::getChannel).flatMap(channel -> channel.createMessage(String.format(" '%s' cadastrado com sucesso",author))).then();


    }
}
