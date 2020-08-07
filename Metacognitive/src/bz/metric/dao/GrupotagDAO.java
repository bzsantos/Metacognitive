package bz.metric.dao;

import static bz.metric.dao.Conexao.getConnection;
import bz.metric.model.Grupotags;
import bz.metric.model.Grupo;
import bz.metric.model.Metatags;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import metamodel.MetaModel;

/**
 *
 * @author bzsantos
 */
public class GrupotagDAO extends Grupotags {

    public boolean insereprocedure() {

        Connection conn = Conexao.getConnection();
        PreparedStatement pstmt;
       // CallableStatement callableStatement = null;

        boolean retorno = true;

        try {

            JOptionPane.showMessageDialog(null, "teste tag: " + this.getTags());

            CallableStatement start = conn.prepareCall("{CALL sp_grupofolks(?)}");

            start.setString(1, this.getTags());

            start.executeUpdate();
            start.close();

            conn.close();
            
           
            
        } catch (SQLException ex) {
            retorno = false;
            ex.printStackTrace();
        }
        return retorno;

    }

    public boolean insereproceduremeta() {

        Connection conn = Conexao.getConnection();
        PreparedStatement pstmt;
       // CallableStatement callableStatement = null;

        boolean retorno = true;

        try {

            CallableStatement start = conn.prepareCall("{CALL sp_metatags()}");
            
         

            // start.setString(1, this.getTags());                   
            start.executeUpdate();
            start.close();

            conn.close();
        } catch (SQLException ex) {
            retorno = false;
            ex.printStackTrace();
        }
        return retorno;

    }

}
