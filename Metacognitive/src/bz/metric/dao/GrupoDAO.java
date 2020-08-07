/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bz.metric.dao;

import static bz.metric.dao.Conexao.getConnection;
import bz.metric.model.Grupotags;
import bz.metric.model.Grupo;
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
public class GrupoDAO {
     private static MetatagDAO instance;

    static {
        instance = new MetatagDAO();
    }

    public GrupoDAO() {
    }

    public static MetatagDAO getInstance() {
        return instance;
    }

    public Grupo read(Grupo dados) {

        PreparedStatement pstmt = null;

        int updateQuery = 0;

        Grupo dadosAux = new Grupo();
        Connection conn = Conexao.getConnection();
        boolean retorno = true;

        try {
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM grupo WHERE idgrupo ='" + dados.getIdgrupo()+ "'");
            // ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE id_usuario =" + dados.getId_usuario()) ;

            if (rs.next()) {
                dadosAux = carregaGrupo(dados, rs);

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
    
    
    

    private Grupo carregaGrupo(Grupo dadosAux, ResultSet rs) throws SQLException {

        //Grupo dadosAux = new Grupo();
        dadosAux.setIdgrupo(rs.getInt("idgrupo"));
        dadosAux.setAssunto(rs.getString("grupoassunto"));    
    

        return dadosAux;

    }
    
    
    
    public Grupo grupos(Grupo dadosAux) {

        PreparedStatement pstmt = null;

        int updateQuery = 0;

       // Grupo dadosAux = new Grupo();
        Connection conn = Conexao.getConnection();
        boolean retorno = true;

        try {
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM grupo WHERE idgrupo ='" + dadosAux.getIdgrupo()+ "'");
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
    
    
    
        private Grupo carregaMetaGrupo(Grupo dadosx, ResultSet rs) throws SQLException {

        //Grupo dadosAux = new Grupo();
        dadosx.setIdgrupo(rs.getInt("idgrupo"));
        dadosx.setAssunto(rs.getString("grupoassunto"));
       
    

        return dadosx;

    }
    
    

    public boolean delete(Grupo dados) {

        boolean retorno = true;
        Connection conn = Conexao.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM grupo WHERE idgrupo = " + dados.getIdgrupo());
            conn.close();
        } catch (SQLException ex) {
            retorno = false;
            ex.printStackTrace();
        }
        return retorno;

    }

    public boolean create(Grupo dados) {

        Connection conn = Conexao.getConnection();
        PreparedStatement pstmt;
        boolean retorno = true;
        try {
            pstmt = conn.prepareStatement(
                    "INSERT INTO grupo (idgrupo, grupoassunto) VALUES (?, ?)");

            pstmt.setInt(1, dados.getIdgrupo());
            pstmt.setString(2, dados.getAssunto());
                

            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            retorno = false;
            ex.printStackTrace();
        }
        return retorno;

    }

    public boolean update(Grupo dados) {

        boolean retorno = true;
        Connection conn = Conexao.getConnection();
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(
                    "UPDATE metatags SET "
                    + "idgrupo = ?,grupoassunto = ?"
                    + "WHERE idgrupo = " + dados.getIdgrupo());

            pstmt.setInt(1, dados.getIdgrupo());
            pstmt.setString(2, dados.getAssunto());        
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            retorno = false;
            ex.printStackTrace();
        }
        return retorno;

    }

    public Grupo findByTag(String tag) throws SQLException {

        String select = "SELECT * FROM grupo WHERE grupoassunto = ?";
        Grupo dadosAux = null;

        try (PreparedStatement stmt = getConnection().prepareStatement(select)) {
            stmt.setString(1, tag);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    dadosAux.setIdgrupo(rs.getInt("idgrupo"));
                    dadosAux.setAssunto(rs.getString("grupoassunto"));
                               
                }
            }
        }
        return dadosAux;
    }
    
    
    
    public List<Grupo> findGrupoTags() throws SQLException {
        List<Grupo> contatos = new ArrayList<>();
        String select = "SELECT * FROM metatags";
        //String select = "select folks, tags, grupo from grupotag group by tags order by folks desc";
        try (PreparedStatement stmt = getConnection().prepareStatement(select); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Grupo dadosAux = new Grupo();
                dadosAux.setIdgrupo(rs.getInt("idgrupo"));
                    dadosAux.setAssunto(rs.getString("grupoassunto"));
                                    

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
                dadosAux.setIdgruupo(rs.getInt("idgruupotag"));
                    dadosAux.setFolks(rs.getInt("folks"));
                    dadosAux.setTags(rs.getString("tags"));
                    dadosAux.setGrupo(rs.getInt("grupo"));                  

                contatos.add(dadosAux);
            }
        }
        return contatos;
    }
    
}
