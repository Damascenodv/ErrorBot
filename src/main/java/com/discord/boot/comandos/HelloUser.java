package com.discord.boot.comandos;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
@Entity
@Table(name = "tb_com_commandos")
public class HelloUser {

    @Id
    @Column(name = "com_codigo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer comCodigo;

    @Column(name ="com_comando")
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
