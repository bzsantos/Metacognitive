/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metamodel;

import bz.metric.control.MetricControl;
import bz.metric.dao.CadastroTagDAO;
import bz.metric.dao.GrupotagDAO;
import bz.metric.model.Metaini;
import bz.metric.nac.MetricNac;
import bz.metric.visual.JMetric;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author bzsantos
 */
public class MetaModel {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        
                  
        
        
        
        
        MetricNac mnac = new MetricNac();
        MetricControl mc = new MetricControl();
        CadastroTagDAO ct =  new CadastroTagDAO();
        
        GrupotagDAO gt =  new GrupotagDAO();
        Metaini mt = new Metaini();
        
       //dmetainicio, user, tagini, linktag, grupo_id, sugestao_metamodelo
      
        //mnac.mets();
        
        //JOptionPane.showMessageDialog(null, "Valor do NAC: " + mnac.kmetrics("pêssego"));
        
        // JOptionPane.showMessageDialog(null, "Valor do NAC: " + mc.cadmetric("pêssego"));
        /**
        String decide = JOptionPane.showInputDialog(null, "1 - Deseja cadastrar um novo metadado? \n  2 - Verificar NAC de um metadado e sugestão de outros");
        
        if (decide.equals("1")){
            
            //idmetainicio, user, tagini, linktag, grupo_id, sugestao_metamodelo
            
            //mt.setIdmeta(3);
//            mt.setUser(1);
//            mt.setTagini(JOptionPane.showInputDialog(null, "Nome do metadado?"));
//            mt.setLink(JOptionPane.showInputDialog(null, "Link do Metadado"));
//            mt.setGrupo(1);
//            
//            
//            ct.create(mt);
            //mt.setGrupoass(0);       
            
            gt.insereproceduremeta();
            
        } else {
            
            gt.setTags(JOptionPane.showInputDialog(null, "Qual metadado quer avalir, digite:" ));
           
            gt.insereprocedure();
            
            
              //mc.cadmetric(gt.getTags());
            
              mnac.kmetrics(gt.getTags());
            
//            String fil = JOptionPane.showInputDialog(null,"1- Deseja filtra: sim ou não?" );
//            
//            if (fil.equals("sim")){
//                JMetric m = new JMetric();
//                m.show(true);
//            }
//            JOptionPane.showMessageDialog(null, "Processo parado");
//            
            } 
        }
        **/
        
        //gt.setTags("maça");
        
        //gt.insereprocedure();
        
        mc.cadmetric("maça");
            
        //JOptionPane.showMessageDialog(null,"nac: " + mnac.kmetrics("cd"));
       
       
    }
        
    }

 
    

