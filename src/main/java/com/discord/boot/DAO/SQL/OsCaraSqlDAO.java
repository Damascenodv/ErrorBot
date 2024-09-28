package com.discord.boot.DAO.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import com.discord.boot.DAO.interfaces.RepositorioJpql;
import com.discord.boot.entity.OsCara;

import jakarta.transaction.Transactional;

@Component
public class OsCaraSqlDAO extends BaseRepositorioSQL implements RepositorioJpql<OsCara> {
    private final static String CAMPOS = "oc_codigo, oc_nome_real, oc_user_code, oc_user_tag, oc_username ";
    private final static String SELECT_ALL = "SELECT " + CAMPOS + " FROM tb_oc_os_cara;";

    private final static String SELECT_GET_BY_CODIGO = "SELECT" + CAMPOS
            + "FROM tb_oc_os_cara WHERE oc_user_code = ? limit 1";
    private final static String DELETE_ITEM = "DELETE FROM tb_oc_os_cara WHERE oc_user_code = ?;";

    private final static String INSERT = "INSERT INTO tb_oc_os_cara (  oc_nome_real ,  oc_user_code ,  oc_user_tag ,  oc_username ) VALUES (?, ?, ?, ?);";

    private final static String UPDATE = "UPDATE tb_oc_os_cara SET oc_nome_real  = ?,  oc_user_code  = ?,  oc_user_tag  = ?,  oc_username  = ? WHERE  oc_user_code  = ?";

    public OsCaraSqlDAO(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<OsCara> getAll() {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = dataSource.getConnection();
            pStmt = con.prepareStatement(SELECT_ALL);

            rs = pStmt.executeQuery();

            return resultSetObjectListTrasnfer(rs);

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            try {
                rs.close();
                pStmt.close();
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        return null;
    }

    public static List<OsCara> resultSetObjectListTrasnfer(ResultSet rs) throws SQLException {
        List<OsCara> osCaras = new ArrayList<>();

        while (rs.next()) {
            OsCara osCara = new OsCara();
            osCara.setOcCodigo((Integer) rs.getObject("oc_codigo"));
            osCara.nomeReal = rs.getString("oc_nome_real");
            osCara.username = rs.getString("oc_user_code");
            osCara.userTag = rs.getString("oc_user_tag");

            osCaras.add(osCara);
        }
        return osCaras;

    }

    public static OsCara resultSetObjectTrasnfer(ResultSet rs) throws SQLException {

        OsCara osCara = new OsCara();
        osCara.setOcCodigo((Integer) rs.getObject("oc_codigo"));
        osCara.nomeReal = rs.getString("oc_nome_real");
        osCara.username = rs.getString("oc_user_code");
        osCara.userTag = rs.getString("oc_user_tag");

        return osCara;

    }

    @Override
    public OsCara getAllbyPK(OsCara osCara) {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = dataSource.getConnection();
            pStmt = con.prepareStatement(SELECT_GET_BY_CODIGO);
            int i = 0;
            pStmt.setObject(i++, osCara.userCode);

            rs = pStmt.executeQuery();

            return (OsCara) resultSetObjectTrasnfer(rs);

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            try {
                rs.close();
                pStmt.close();
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        return null;
    }

    @Override
    @Transactional
    public OsCara insert(OsCara osCara) throws Exception {
        PreparedStatement pStmt = null;
        Connection con = dataSource.getConnection();
        try {
            pStmt = con.prepareStatement(INSERT);
            int i = 1;
            pStmt.setObject(i++, osCara.nomeReal);
            pStmt.setObject(i++, osCara.userCode);
            pStmt.setObject(i++, osCara.userTag);
            pStmt.setObject(i++, osCara.username);

            pStmt.execute();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            try {
                if (pStmt != null) {
                    pStmt.close();
                }
                if (con != null) {
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
        try {
            con = dataSource.getConnection();
            pStmt = con.prepareStatement(UPDATE);
            int i = 1;
            pStmt.setObject(i++, osCara.nomeReal);
            pStmt.setObject(i++, osCara.userCode);
            pStmt.setObject(i++, osCara.userTag);
            pStmt.setObject(i++, osCara.username);

            pStmt.setObject(i++, osCara.userCode);

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
                }
                if (con != null) {
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
    public void delete(OsCara osCara) {
        PreparedStatement pStmt = null;
        Connection con = null;
        try {
            con = dataSource.getConnection();
            pStmt = con.prepareStatement(DELETE_ITEM);
            int i = 0;
            pStmt.setObject(i++, osCara.userCode);
        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            try {
                if (pStmt != null) {
                    pStmt.close();
                }
                if (con != null) {
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

    }
}
