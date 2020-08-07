/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bz.metric.control;

import bz.metric.dao.MetDAO;
import bz.metric.dao.MetatagDAO;
import bz.metric.dao.MetricDAO;
import bz.metric.model.Metaini;
import bz.metric.model.Metatags;
import bz.metric.model.Metrica;
import bz.metric.nac.MetricNac;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author bzsantos
 */
public class MetricControl {
    
    private int id; 
    private int num; 

    public void cadmetric(String tag) throws SQLException {

        Metrica metrica = new Metrica();       

        MetricDAO me = new MetricDAO();
        MetricNac n = new MetricNac();

        n.kmetrics(tag);

        MetatagDAO grupodao = new MetatagDAO();
        List<Metatags> resultado = grupodao.findGrupoTags();

        resultado.get(0).getFolks();

        for (int i = 0; i < resultado.size(); i++) {

            if (resultado.get(i).getTags().equals(tag)) {

//                int id; 
//                int num; 

//                id = resultado.get(i).getIdmetatag();
//                num = resultado.get(i).getFolks();
                
                this.setId(resultado.get(i).getIdmetatag());
                this.setNum(resultado.get(i).getFolks());
                
                JOptionPane.showMessageDialog(null,"id: " + this.getId());

                JOptionPane.showMessageDialog(null, "folks: " + this.getNum());

                grupodao.findGrupoTags().get(0);

                //metrica.setIdmetrica(1);
                //metrica.setTaxodicio(1);
                metrica.setMetatag(id);
                metrica.setKma(n.ka());
                metrica.setKmb(n.kb());
                metrica.setNac(n.nc());
                metrica.setQtcompartilhada(num);
                metrica.setTagm(tag);
         //     metrica.setLink("");
         //     metrica.setGassunto("");

                me.criametrica(metrica);

            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    
    
    

}
