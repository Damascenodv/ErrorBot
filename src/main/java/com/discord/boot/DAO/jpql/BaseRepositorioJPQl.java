package com.discord.boot.DAO.jpql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class BaseRepositorioJPQl {
    @PersistenceContext
    @Autowired
    protected EntityManager manager;


}
