package com.discord.boot.listeners;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.discord.boot.DAO.SQL.OsCaraSqlDAO;
import com.discord.boot.DAO.interfaces.RepositorioJpql;
import com.discord.boot.configuration.DiscordBotConfiguration;
import com.discord.boot.entity.ConsultaRequest;
import com.discord.boot.entity.OsCara;

import discord4j.core.object.entity.Message;
import reactor.core.publisher.Mono;

@Component
public abstract class MessageListener {
    private String author = "UNKNOWN";
    private RepositorioJpql<OsCara> repositorio = new OsCaraSqlDAO(DiscordBotConfiguration.dataSource());

    public Mono<Void> processMessageOC(final Message eventMessage) {
        return montarMensagem(eventMessage, String.format("alo '%s'", author));
    }

    public Mono<Void> processMessageBolsonaro(final Message eventMessage) {
        return montarMensagem(eventMessage, "hello Bolsonaro");
    }

    public Mono<Void> processCadastroUsuario(final Message eventMessage) {
        return cadastraPessoa(eventMessage);
    }

    public Mono<Void> processAtualizaUsuario(final Message eventMessage) {
        OsCara osCara = new OsCara(eventMessage);
        osCara.nomeReal = "joão Damasceno";
        return atualizarPessoa(eventMessage, osCara);
    }

    public Mono<Void> processCalculo(final Message eventMessage) {
        return calcularDosimetira(eventMessage);
    }

    private Mono<Void> montarMensagem(Message eventMessage, String mensagem) {
        return Mono.just(eventMessage).filter(message -> {
            final Boolean isNotBot = message.getAuthor()
                    .map(user -> !user.isBot())
                    .orElse(false);

            if (isNotBot) {

                message.getAuthor().ifPresent(user -> author = user.getUsername());
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

    private Mono<Void> cadastraPessoa(Message eventMessage) {
        return Mono.just(eventMessage).filter(message -> {
            final Boolean isNotBot = message.getAuthor()
                    .map(user -> !user.isBot())
                    .orElse(false);

            if (isNotBot) {

                message.getAuthor().ifPresent(user -> author = user.getUsername());
                OsCara osCara = new OsCara(eventMessage);
                try {
                    repositorio.insert(osCara);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            return isNotBot;

        }).flatMap(Message::getChannel)
                .flatMap(channel -> channel.createMessage(String.format(" '%s' cadastrado com sucesso", author)))
                .then();

    }

    private Mono<Void> atualizarPessoa(Message eventMessage, OsCara osCara) {
        return Mono.just(eventMessage).filter(message -> {
            final Boolean isNotBot = message.getAuthor()
                    .map(user -> !user.isBot())
                    .orElse(false);

            if (isNotBot) {

                message.getAuthor().ifPresent(user -> author = user.getUsername());
                try {
                    repositorio.update(osCara);

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            return isNotBot;

        }).flatMap(Message::getChannel)
                .flatMap(channel -> channel.createMessage(String.format(" '%s' Atualizado com sucesso", author)))
                .then();

    }

    private Mono<Void> calcularDosimetira(Message eventMessage) {
        String[] resultado = eventMessage.getContent().split("/");
        int casoCodigo;
        Double baseLegal;
             casoCodigo = Integer.parseInt(resultado[1]);
             baseLegal = Double.parseDouble(resultado[2]);
        
           
        return Mono.just(eventMessage).filter(message -> {
            final Boolean isNotBot = message.getAuthor()
                    .map(user -> !user.isBot())
                    .orElse(false);
            if (isNotBot) {
                message.getAuthor().ifPresent(user -> author = user.getUsername());
                
            }
            return isNotBot;
        }).flatMap(Message::getChannel)
                .flatMap(channel -> channel.createMessage(String.format(" '%s' Atualizado com sucesso", realizarConsulta(new ConsultaRequest(casoCodigo,baseLegal)))))
                .then();

    }

    public String realizarConsulta(ConsultaRequest request) {

        String url = "http://localhost:8083/tawaProject/fase1";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ConsultaRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                String.class);

        return response.getBody();
    }
}
