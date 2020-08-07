package bz.metric.dao;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public final class Conexao {

    //Nao devem ser criadas instancias de Concexao fora desta classe.
    private Conexao(){
     PreparedStatement pstmt = null;
    }

    static {
        try {
            //Carrega o driver do banco
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver do banco nao encontrado.");
            System.exit( -1);
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
         
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/meta",
                    "root",
                    ""
                    );
        } catch (SQLException ex) {
            System.err.print("Erro ao obter conexao: " + ex.getMessage());
        }
        return conn;
    }

}
