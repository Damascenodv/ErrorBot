package com.discord.boot.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_hl_high_lights")
public class Highlights {

    @Id
    @Column(name = "hl_codigo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer hlCodigo;

    @Column(name = "hl_quotes")
    public   String  quotes;

    @ManyToMany
    @JoinTable(
            name = "tb_hlc_high_light_dos_cara",
            joinColumns = @JoinColumn(name = "oc_codigo")
    )
    public List<OsCara> osCara;


}
