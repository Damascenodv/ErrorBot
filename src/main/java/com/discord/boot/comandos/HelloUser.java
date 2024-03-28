package com.discord.boot.comandos;

import org.springframework.boot.autoconfigure.domain.EntityScan;


public class HelloUser {

    private  String comando  = "OC";



    public HelloUser(String comando) {
        this.comando = comando;
    }

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

}
