/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bz.metric.dao;

import bz.metric.model.Metrica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author bzsantos
 */
public class MetDAO extends GenericDAO {
    
        public boolean criametrica(Metrica dados) {

        Connection conn = Conexao.getConnection();
        PreparedStatement pstmt;
        boolean retorno = true;
        try {
            pstmt = conn.prepareStatement(
                    "INSERT INTO metrica(metatag,kma,kmb,nac,qt_compartilhada,tagm) VALUES (?,?,?,?,?,?)");

            //pstmt.setInt(1, dados.getIdmetrica());            
            pstmt.setInt(1, dados.getMetatag());
            pstmt.setDouble(2, dados.getKma());
            pstmt.setDouble(3, dados.getKmb());
            pstmt.setDouble(4, dados.getNac());
            pstmt.setInt(5, dados.getQtcompartilhada());
            pstmt.setString(6, dados.getTagm());
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
    
}
