package bz.metric.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GenericDAO {

    private final Connection connection;

    protected GenericDAO() {
        this.connection = Conexao.getConnection();
    }

    protected Connection getConnection() {
        return connection;
    }

    protected void save(String insertSql, Object... parametros) throws
            SQLException {
        try (PreparedStatement pstmt = getConnection().prepareStatement(insertSql)) {
            for (int i = 0; i < parametros.length; i++) {
                pstmt.setObject(i + 1, parametros[i]);
            }
            pstmt.execute();
        }
    }

    protected void update(String updateSql, Object id, Object... parametros)
            throws SQLException {
        try (PreparedStatement pstmt = getConnection().prepareStatement(updateSql)) {
            for (int i = 0; i < parametros.length; i++) {
                pstmt.setObject(i + 1, parametros[i]);
            }
            pstmt.setObject(parametros.length + 1, id);
            pstmt.execute();
        }
    }

    protected void delete(String deleteSql, Object... parametros) throws
            SQLException {
        try (PreparedStatement pstmt = getConnection().prepareStatement(deleteSql)) {
            for (int i = 0; i < parametros.length; i++) {
                pstmt.setObject(i + 1, parametros[i]);
            }
            pstmt.execute();
        }
    }
}
