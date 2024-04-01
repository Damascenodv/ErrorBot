package com.discord.boot.DAO.SQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component

public class BaseRepositorioSQL {
    protected final DataSource dataSource;


    @Autowired
    public BaseRepositorioSQL(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection obterConexao() throws SQLException {
        return dataSource.getConnection();
    }
}
