package com.discord.boot.DAO.SQL;

import com.discord.boot.DAO.interfaces.RepositorioJpql;
import com.discord.boot.entity.OsCara;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
@Component
public class OsCaraSqlDAO extends  BaseRepositorioSQL implements RepositorioJpql<OsCara> {

    public OsCaraSqlDAO(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<OsCara> getAll() {
        return null;
    }

    @Override
    public OsCara getAllbyPK(Integer codigo) {
        return null;
    }

    @Override
    @Transactional
    public OsCara insert(OsCara osCara) throws Exception{
            PreparedStatement pStmt = null;
            Connection con = dataSource.getConnection();

            String insert = " INSERT INTO `errorboot`.`tb_oc_os_cara` ( `oc_nome_real`, `oc_user_code`, `oc_user_tag`, `oc_username`) VALUES (?, ?, ?, ?);";
            try {
                pStmt = con.prepareStatement(insert);
                int i = 1;
                pStmt.setObject(i++, osCara.nomeReal);
                pStmt.setObject(i++,  osCara.userCode);
                pStmt.setObject(i++, osCara.userTag);
                pStmt.setObject(i++,osCara.username);

                pStmt.execute();
            } catch (SQLException ex) {
                throw ex;
            } finally {
                try {
                    if (pStmt != null) {
                        pStmt.close();
                    }if(con != null){
                        con.close();
                    }
                } catch (SQLException e) {
                    throw e;
                }
            }
        return osCara;


    }

    @Override
    public OsCara update(OsCara osCara) {
        PreparedStatement pStmt = null;
        Connection con = null;

        String UPDATE = "UPDATE `errorboot`.`tb_oc_os_cara` SET `oc_nome_real` = ?, `oc_user_code` = ?, `oc_user_tag` = ?, `oc_username` = ? WHERE `oc_user_code` = ?";
        try {
            con = dataSource.getConnection();
            pStmt = con.prepareStatement(UPDATE);
            int i = 1;
            pStmt.setObject(i++, osCara.nomeReal);
            pStmt.setObject(i++,  osCara.userCode);
            pStmt.setObject(i++, osCara.userTag);
            pStmt.setObject(i++,osCara.username);

            pStmt.setObject(i++,osCara.userCode);

            pStmt.execute();
        } catch (Exception ex) {
            try {
                throw ex;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } finally {
            try {
                if (pStmt != null) {
                    pStmt.close();
                }if(con != null){
                    con.close();
                }
            } catch (SQLException e) {
                try {
                    throw e;
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        return osCara;
    }

    @Override
    public void delete(OsCara obj) {

    }
}
