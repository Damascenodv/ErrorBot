package com.discord.boot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_hlc_high_light_dos_cara")
public class HighLightDosCara {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  hlc_codigo;







}
