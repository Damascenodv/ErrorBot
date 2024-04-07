package com.discord.boot.entity;

import discord4j.core.object.entity.Message;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_oc_os_cara")
public class OsCara {
    public OsCara(Message message){
        message.getAuthor().ifPresent(user ->this.username = user.getUsername());
        message.getAuthor().ifPresent(user ->this.userCode = user.getId().toString());
        message.getAuthor().ifPresent(user ->this.userTag = user.getTag());

    }
    public  OsCara(){}
    @Id
    @Column(name = "oc_codigo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ocCodigo;

    @Column(name = "oc_username", nullable = false)
    public String username;

    @Column(name = "oc_userCode", nullable = true)
    public String userCode;

    @Column(name = "oc_nome_real", nullable = true)
    public String nomeReal;

    @Column(name = "oc_user_tag", nullable = false)
    public String userTag;

    @ManyToMany(mappedBy = "osCara")
    public List<Highlights> highlights;


    public Integer getOcCodigo() {
        return ocCodigo;
    }

    public void setOcCodigo(Integer ocCodigo) {
        this.ocCodigo = ocCodigo;
    }
}
