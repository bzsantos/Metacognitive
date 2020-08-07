/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bz.metric.dao;

import bz.metric.model.Metaini;
import bz.metric.model.Metrica;
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
public class MetricDAO extends GenericDAO {

    private static MetricDAO instance;

    static {
        instance = new MetricDAO();
    }

    public MetricDAO() {
    }

    public static MetricDAO getInstance() {
        return instance;
    }

    public boolean criametrica(Metrica dados) {

        Connection conn = Conexao.getConnection();
        PreparedStatement pstmt;
        boolean retorno = true;
        try {
            pstmt = conn.prepareStatement(
                    "INSERT INTO metric(idmetrica,metatag,kma,kmb,nac,qt_compartilhada,tagm) VALUES (?,?,?,?,?,?,?)");

            pstmt.setInt(1, dados.getIdmetrica());            
            pstmt.setInt(2, dados.getMetatag());
            pstmt.setDouble(3, dados.getKma());
            pstmt.setDouble(4, dados.getKmb());
            pstmt.setDouble(5, dados.getNac());
            pstmt.setInt(6, dados.getQtcompartilhada());
            pstmt.setString(7, dados.getTagm());
//            pstmt.setString(9, dados.getLink());
//            pstmt.setString(10, dados.getGassunto());

            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
          //  retorno = false;
            ex.printStackTrace();
        }
        return retorno;

    }

    public Metaini lermetaini(Metaini dadosAux) {

        PreparedStatement pstmt = null;

        int updateQuery = 0;

        // Metatags dadosAux = new Metatags();
        Connection conn = Conexao.getConnection();
        boolean retorno = true;

        try {
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM metainicio WHERE idmetainicio ='" + dadosAux.getIdmeta() + "'");
            // ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE id_usuario =" + dados.getId_usuario()) ;

            if (rs.next()) {
                dadosAux = carregaMetaIni(dadosAux, rs);

            } else {
                dadosAux = null;
            }

            //  statement.executeUpdate("INSERT INTO user" + "VALUES('" + dado.getId() + "'");
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
          //  retorno = false;
        }
        return dadosAux;

    }

    private Metaini carregaMetaIni(Metaini dadosx, ResultSet rs) throws SQLException {

        //Metatags dadosAux = new Metatags();
        dadosx.setIdmeta(rs.getInt("idmetainicio"));
        dadosx.setUser(rs.getInt("user"));
        dadosx.setTagini(rs.getString("tagini"));
        dadosx.setLink(rs.getString("linktag"));
        dadosx.setGrupo(rs.getInt("grupo_id"));

        return dadosx;

    }
    
        public List<Metaini> findMetaini() throws SQLException {
        List<Metaini> contatos = new ArrayList<>();
        String select = "SELECT * FROM metainicio";
        //String select = "select folks, tags, grupo from grupotag group by tags order by folks desc";
        try (PreparedStatement stmt = getConnection().prepareStatement(select); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Metaini dadosAux = new Metaini();
             dadosAux.setIdmeta(rs.getInt("idmetainicio"));
             dadosAux.setUser(rs.getInt("user"));
             dadosAux.setTagini(rs.getString("tagini"));
             dadosAux.setLink(rs.getString("linktag"));
             dadosAux.setGrupo(rs.getInt("grupo_id"));
                                      

                contatos.add(dadosAux);
            }
        }
        return contatos;
    }
    
    
    

}
