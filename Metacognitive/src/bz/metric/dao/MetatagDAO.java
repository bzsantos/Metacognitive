/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bz.metric.dao;

import bz.metric.model.Metatags;
import bz.metric.model.Grupotags;
import bz.metric.model.Metasort;
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
public class MetatagDAO extends GenericDAO {

    private static MetatagDAO instance;

    static {
        instance = new MetatagDAO();
    }

    public MetatagDAO() {
    }

    public static MetatagDAO getInstance() {
        return instance;
    }

    public Metatags read(Metatags dados) {

        PreparedStatement pstmt = null;

        int updateQuery = 0;

        Metatags dadosAux = new Metatags();
        Connection conn = Conexao.getConnection();
        boolean retorno = true;

        try {
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM metatags WHERE idmetatag ='" + dados.getIdmetatag() + "'");
            // ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE id_usuario =" + dados.getId_usuario()) ;

            if (rs.next()) {
                dadosAux = carregaMetatags(dados, rs);

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

    private Metatags carregaMetatags(Metatags dadosAux, ResultSet rs) throws SQLException {

        //Metatags dadosAux = new Metatags();
        dadosAux.setIdmetatag(rs.getInt("idmetatag"));
        dadosAux.setGrupos(rs.getInt("grupo"));
        dadosAux.setFolks(rs.getInt("folksmax"));
        dadosAux.setTags(rs.getString("tags"));

        return dadosAux;
    }

    public Grupotags lergrupo(Grupotags dadosAux) {

        PreparedStatement pstmt = null;

        int updateQuery = 0;

        // Metatags dadosAux = new Metatags();
        Connection conn = Conexao.getConnection();
        boolean retorno = true;

        try {
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM grupotag WHERE idgrupotag ='" + dadosAux.getIdgruupo() + "'");
            // ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE id_usuario =" + dados.getId_usuario()) ;

            if (rs.next()) {
                dadosAux = carregaMetaGrupo(dadosAux, rs);

            } else {
                dadosAux = null;
            }

            //  statement.executeUpdate("INSERT INTO user" + "VALUES('" + dado.getId() + "'");
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            retorno = false;
        }
        return dadosAux;

    }

    private Grupotags carregaMetaGrupo(Grupotags dadosx, ResultSet rs) throws SQLException {

        //Metatags dadosAux = new Metatags();
        dadosx.setIdgruupo(rs.getInt("idgrupotag"));
        dadosx.setMetasort(rs.getInt("metasort"));
        dadosx.setFolks(rs.getInt("folks"));
        dadosx.setTags(rs.getString("tags"));
        dadosx.setGrupo(rs.getInt("grupo"));

        return dadosx;

    }

    public boolean create(Metatags dados) {

        Connection conn = Conexao.getConnection();
        PreparedStatement pstmt;
        boolean retorno = true;
        try {
            pstmt = conn.prepareStatement(
                    "INSERT INTO metatags (idmetatag, grupo, folksmax, tags, grupo) VALUES (?, ?, ?, ?, ?)");

            pstmt.setInt(1, dados.getIdmetatag());
            pstmt.setInt(2, dados.getFolks());
            pstmt.setString(3, dados.getTags());
            pstmt.setInt(4, dados.getGrupos());

            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            retorno = false;
            ex.printStackTrace();
        }
        return retorno;

    }

    public boolean delete(Metatags dados) {

        boolean retorno = true;
        Connection conn = Conexao.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM metatags WHERE idmetatag = " + dados.getIdmetatag());
            conn.close();
        } catch (SQLException ex) {
            retorno = false;
            ex.printStackTrace();
        }
        return retorno;

    }

    public boolean update(Metatags dados) {

        boolean retorno = true;
        Connection conn = Conexao.getConnection();
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(
                    "UPDATE metatags SET "
                    + "id_metatag = ?, grupo = ?, folksmax = ?, tags = ?,grupo = ?"
                    + "WHERE idmetatag = " + dados.getIdmetatag());

            pstmt.setInt(1, dados.getIdmetatag());
            pstmt.setInt(2, dados.getGrupos());
            pstmt.setInt(3, dados.getFolks());
            pstmt.setString(4, dados.getTags());
            pstmt.setInt(5, dados.getGrupos());
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            retorno = false;
            ex.printStackTrace();
        }
        return retorno;

    }

    public Metatags findByTag(String tag) throws SQLException {

        String select = "SELECT * FROM metatags WHERE tags = ?";
        Metatags dadosAux = null;

        try (PreparedStatement stmt = getConnection().prepareStatement(select)) {
            stmt.setString(1, tag);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    dadosAux.setIdmetatag(rs.getInt("idmetatag"));
                    dadosAux.setGrupos(rs.getInt("grupo"));
                    dadosAux.setFolks(rs.getInt("folksmax"));
                    dadosAux.setTags(rs.getString("tags"));

                }
            }
        }
        return dadosAux;
    }

    public List<Metatags> findGrupoTags() throws SQLException {
        List<Metatags> contatos = new ArrayList<>();
        String select = "SELECT * FROM metatags";
        //String select = "select folks, tags, grupo from grupotag group by tags order by folks desc";
        try (PreparedStatement stmt = getConnection().prepareStatement(select); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Metatags dadosAux = new Metatags();
                dadosAux.setIdmetatag(rs.getInt("idmetatags"));
                dadosAux.setGrupos(rs.getInt("grupo"));
                dadosAux.setFolks(rs.getInt("folksmax"));
                dadosAux.setTags(rs.getString("tags"));

                contatos.add(dadosAux);
            }
        }
        return contatos;
    }

    public List<Grupotags> listaGrupo() throws SQLException {
        List<Grupotags> contatos = new ArrayList<>();
        String select = "SELECT * FROM grupotag";
        //String select = "select folks, tags, grupo from grupotag group by tags order by folks desc";
        try (PreparedStatement stmt = getConnection().prepareStatement(select); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Grupotags dadosAux = new Grupotags();

                dadosAux.setIdgruupo(rs.getInt("idgrupotag"));
                dadosAux.setMetasort(rs.getInt("metasort"));
                dadosAux.setFolks(rs.getInt("folks"));
                dadosAux.setTags(rs.getString("tags"));
                dadosAux.setGrupo(rs.getInt("grupo"));

                contatos.add(dadosAux);
            }
        }
        return contatos;
    }

    public int contfolks() throws SQLException {
        // List<Metasort> contatos = new ArrayList<>();
        String sql = "select count(tagsort) from metasort INNER JOIN grupotag ON gruposort = grupo";

        //String select = "select folks, tags, grupo from grupotag group by tags order by folks desc";
        try (Statement stmt = getConnection().createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                return rs.getInt(1);

            }
            // return 0;

        } catch (Exception e) {

        }
        return 0;

    }
    
        public int contfolksmeta() throws SQLException {
        // List<Metasort> contatos = new ArrayList<>();
        String sql = "select count(folksmax) from metatags";

        //String select = "select folks, tags, grupo from grupotag group by tags order by folks desc";
        try (Statement stmt = getConnection().createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                return rs.getInt(1);

            }
            // return 0;

        } catch (Exception e) {

        }
        return 0;

    }
}
