/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bz.metric.dao;

import bz.metric.model.Grupotags;
import bz.metric.model.CadastroTag;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bzsantos
 */
public class CadastroTagDAO extends GenericDAO {

    private static CadastroTagDAO instance;

    static {
        instance = new CadastroTagDAO();
    }

    public CadastroTagDAO() {
    }

    public static CadastroTagDAO getInstance() {
        return instance;
    }

    public CadastroTag read(CadastroTag dados) {

        PreparedStatement pstmt = null;

        int updateQuery = 0;

        CadastroTag dadosAux = new CadastroTag();
        Connection conn = Conexao.getConnection();
        boolean retorno = true;

        try {
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM metainicio WHERE idmetainicio ='" + dados.getIdinicio() + "'");
            // ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE id_usuario =" + dados.getId_usuario()) ;

            if (rs.next()) {
                dadosAux = carregaCadastroTag(dados, rs);

            } else {
                dados = null;
            }

            //  statement.executeUpdate("INSERT INTO user" + "VALUES('" + dado.getId() + "'");
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            retorno = false;
        }
        return dados;

    }

    private CadastroTag carregaCadastroTag(CadastroTag dadosAux, ResultSet rs) throws SQLException {

        //CadastroTag dadosAux = new CadastroTag();
        dadosAux.setIdinicio(rs.getInt("idmetainicio"));
        dadosAux.setUser(rs.getInt("user"));
        dadosAux.setTagini(rs.getString("tagini"));
        dadosAux.setLinktag(rs.getString("linktag"));
        dadosAux.setGrupo(rs.getInt("grupo_id"));
        //dadosAux.setSugestao(rs.getInt("sugestao_metamodelo"));

        return dadosAux;

    }

    public boolean delete(CadastroTag dados) {

        boolean retorno = true;
        Connection conn = Conexao.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM metainicio WHERE idmetainicio = " + dados.getIdinicio());
            conn.close();
        } catch (SQLException ex) {
            retorno = false;
            ex.printStackTrace();
        }
        return retorno;

    }

    public boolean create(CadastroTag dados) {

        Connection conn = Conexao.getConnection();
        PreparedStatement pstmt;
        boolean retorno = true;
        try {
            pstmt = conn.prepareStatement(
                    "INSERT INTO metainicio (idmetainicio, user, tagini, linktag, grupo_id) VALUES (?, ?, ?, ?, ?)");

            pstmt.setInt(1, dados.getIdinicio());
            pstmt.setInt(2, dados.getUser());
            pstmt.setString(3, dados.getTagini());
            pstmt.setString(4, dados.getLinktag());
            pstmt.setInt(5, dados.getGrupo());
           // pstmt.setInt(6, dados.getSugestao());

            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            retorno = false;
            ex.printStackTrace();
        }
        return retorno;

    }

    public boolean update(CadastroTag dados) {

        boolean retorno = true;
        Connection conn = Conexao.getConnection();
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(
                    "UPDATE metainicio SET "
                    + "idmetainicio = ?,user = ?, tagini = ?,linktag = ?, grupo_id = ?"
                    + "WHERE idmetainicio = " + dados.getIdinicio());

            pstmt.setInt(1, dados.getIdinicio());
            pstmt.setInt(2, dados.getUser());
            pstmt.setString(3, dados.getTagini());
            pstmt.setString(4, dados.getLinktag());
            pstmt.setInt(5, dados.getGrupo());
          //  pstmt.setInt(6, dados.getSugestao());
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            retorno = false;
            ex.printStackTrace();
        }
        return retorno;

    }

    public CadastroTag findByTagIni(String tag) throws SQLException {

        String select = "SELECT * FROM metainicio WHERE tagini = ?";
        CadastroTag dadosAux = null;

        try (PreparedStatement stmt = getConnection().prepareStatement(select)) {
            stmt.setString(1, tag);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    dadosAux.setIdinicio(rs.getInt("idmetainicio"));
                    dadosAux.setUser(rs.getInt("user"));
                    dadosAux.setTagini(rs.getString("tagini"));
                    dadosAux.setLinktag(rs.getString("linktag"));
                    dadosAux.setGrupo(rs.getInt("grupo_id"));
                  //  dadosAux.setSugestao(rs.getInt("sugestao_metamodelo"));
                }
            }
        }
        return dadosAux;
    }

    public List<CadastroTag> findIniTags() throws SQLException {
        List<CadastroTag> contatos = new ArrayList<>();
        String select = "SELECT * FROM metainicio";
        //String select = "select folks, tags, grupo from grupotag group by tags order by folks desc";
        try (PreparedStatement stmt = getConnection().prepareStatement(select); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                CadastroTag dadosAux = new CadastroTag();
                dadosAux.setIdinicio(rs.getInt("idmetainicio"));
                dadosAux.setUser(rs.getInt("user"));
                dadosAux.setTagini(rs.getString("tagini"));
                dadosAux.setLinktag(rs.getString("linktag"));
                dadosAux.setGrupo(rs.getInt("grupo_id"));
              //  dadosAux.setSugestao(rs.getInt("sugestao_metamodelo"));

                contatos.add(dadosAux);
            }
        }
        return contatos;
    }

}
