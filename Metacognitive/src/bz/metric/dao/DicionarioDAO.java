/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bz.metric.dao;

import bz.metric.model.Dicionario;
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
public class DicionarioDAO extends GenericDAO {

    private static DicionarioDAO instance;

    static {
        instance = new DicionarioDAO();
    }

    public DicionarioDAO() {
    }

    public static DicionarioDAO getInstance() {
        return instance;
    }

    public Dicionario read(Dicionario dados) {

        PreparedStatement pstmt = null;

        int updateQuery = 0;

        Dicionario dadosAux = new Dicionario();
        Connection conn = Conexao.getConnection();
        boolean retorno = true;

        try {
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM taxodicionario WHERE id_dicionario ='" + dados.getId_dicionario() + "'");
            // ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE id_usuario =" + dados.getId_usuario()) ;

            if (rs.next()) {
                dadosAux = carregaDicionario(dados, rs);

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

    private Dicionario carregaDicionario(Dicionario dadosAux, ResultSet rs) throws SQLException {

        //Dicionario dadosAux = new Dicionario();
        dadosAux.setId_dicionario(rs.getInt("id_dicionario"));
        dadosAux.setHierarquia(rs.getInt("hierarquia"));
        dadosAux.setPalavra(rs.getString("palavra"));
        dadosAux.setSinonimo(rs.getString("sinonimo"));
        dadosAux.setIdioma(rs.getString("idioma"));

        return dadosAux;

    }

    public boolean delete(Dicionario dados) {

        boolean retorno = true;
        Connection conn = Conexao.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM taxodicionario WHERE id_dicionario = " + dados.getId_dicionario());
            conn.close();
        } catch (SQLException ex) {
            retorno = false;
            ex.printStackTrace();
        }
        return retorno;

    }

    public boolean create(Dicionario dados) {

        Connection conn = Conexao.getConnection();
        PreparedStatement pstmt;
        boolean retorno = true;
        try {
            pstmt = conn.prepareStatement(
                    "INSERT INTO taxodicionario (id_dicionario, hierarquia, palavra, sinonimo, idioma) VALUES (?, ?, ?, ?, ?)");

            pstmt.setInt(1, dados.getId_dicionario());
            pstmt.setInt(2, dados.getHierarquia());
            pstmt.setString(3, dados.getPalavra());
            pstmt.setString(4, dados.getSinonimo());
            pstmt.setString(5, dados.getIdioma());

            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            retorno = false;
            ex.printStackTrace();
        }
        return retorno;

    }

    public boolean update(Dicionario dados) {

        boolean retorno = true;
        Connection conn = Conexao.getConnection();
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(
                    "UPDATE taxodicionario SET "
                    + "id_dicionario = ?,hierarquia = ?, palavra = ?,sinonimo = ?, idioma = ?"
                    + "WHERE id_dicionario = " + dados.getId_dicionario());

            pstmt.setInt(1, dados.getId_dicionario());
            pstmt.setInt(2, dados.getHierarquia());
            pstmt.setString(3, dados.getPalavra());
            pstmt.setString(4, dados.getSinonimo());
            pstmt.setString(5, dados.getIdioma());
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            retorno = false;
            ex.printStackTrace();
        }
        return retorno;

    }

    public Dicionario findByPalavra(String tag) throws SQLException {

        String select = "SELECT * FROM taxodicionario WHERE palavra = ?";
        Dicionario dadosAux = null;

        try (PreparedStatement stmt = getConnection().prepareStatement(select)) {
            stmt.setString(1, tag);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    dadosAux.setId_dicionario(rs.getInt("id_dicionario"));
                    dadosAux.setHierarquia(rs.getInt("hierarquia"));
                    dadosAux.setPalavra(rs.getString("palavra"));
                    dadosAux.setSinonimo(rs.getString("sinonimo"));
                    dadosAux.setIdioma(rs.getString("idioma"));

                }
            }
        }
        return dadosAux;
    }

    public List<Dicionario> findDicioTags() throws SQLException {
        List<Dicionario> contatos = new ArrayList<>();
        String select = "SELECT * FROM taxodicionario";
        //String select = "select folks, tags, grupo from grupotag group by tags order by folks desc";
        try (PreparedStatement stmt = getConnection().prepareStatement(select); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Dicionario dadosAux = new Dicionario();
                dadosAux.setId_dicionario(rs.getInt("id_dicionario"));
                dadosAux.setHierarquia(rs.getInt("hierarquia"));
                dadosAux.setPalavra(rs.getString("palavra"));
                dadosAux.setSinonimo(rs.getString("sinonimo"));
                dadosAux.setIdioma(rs.getString("idioma"));

                contatos.add(dadosAux);
            }
        }
        return contatos;
    }

}
