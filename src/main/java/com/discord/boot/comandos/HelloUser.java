package com.discord.boot.comandos;

public class HelloUser {
    private static String comando  = "OC";


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
