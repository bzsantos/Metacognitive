/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bz.metric.nac;

import bz.metric.dao.MetatagDAO;
import bz.metric.model.Metatags;
import javax.swing.JOptionPane;
import java.io.*;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author bzsantos
 */
public class MetricMethods extends DadosNac {

    private double EFGP; //Erro de Folksonomia Grande Pessimista
    private double EFMO;
    private double EFGO;
    private double EFMP;
    private String tag1; //primeiro metadado
    private String tag2; //segundo metadado
    private String tag3; //terceiro metadado
    private String tag4; //terceiro metadado
   

    public void metametrics(String metametric) throws SQLException {

        MetatagDAO grupodao = new MetatagDAO();

        List<Metatags> metaemo = grupodao.findGrupoTags();

        this.setTag1(metaemo.get(0).getTags());
        this.setTag2(metaemo.get(1).getTags());
        this.setTag3(metaemo.get(2).getTags());
      //  this.setTag4(metaemo.get(3).getTags());
        
//        JOptionPane.showMessageDialog(null, "TagOne: " + metametric);
//        
//        JOptionPane.showMessageDialog(null, "Tag1: " + this.getTag1());
//        JOptionPane.showMessageDialog(null, "Tag1: " + this.getTag2());
//        JOptionPane.showMessageDialog(null, "Tag1: " + this.getTag3());

        for (int i = 0; i < metaemo.size(); i++) {

            if (metametric.equals(this.getTag1())) {

                this.setEFMO(0.00);//EMO = quantidade de erros do tipo “médio otimista”, em que se estima acertar parcialmente e erra completamente ou se estima acertar completamente e acerta parcialmente o problema.
                this.setEFGP(1.00);//EGP = quantidade de erros do tipo “grande pessimista(gp)”, em que se estima errar e acerta completamente o problema.
                this.setEFMP(1.00);//EMP = quantidade de erros do tipo “médio pessimista”, em que se estima acertar parcialmente e acerta completamente ou se estima errar e acerta parcialmente o problema.
                this.setEFGO(0.00);//EGO = quantidade de erros do tipo “grande otimista(go)”, em que se estima acertar e erra completamente o problema
               // JOptionPane.showMessageDialog(null, "Tag1: " + this.getTag1());
                //break;
            } else if (metametric.equals(this.getTag2())) {

                this.setEFMO(0.50);
                this.setEFGP(0.00);
                this.setEFMP(0.50);
                this.setEFGO(0.00);
               // JOptionPane.showMessageDialog(null, "Tag2: " + this.getTag2());
            } else if (metametric.equals(this.getTag3())) {

                this.setEFMO(0.25);
                this.setEFGP(0.00);
                this.setEFMP(0.25);
                this.setEFGO(0.00);
               // JOptionPane.showMessageDialog(null, "Tag3: " + this.getTag3());
            } else  {
                this.setEFMO(-1.00);
                this.setEFGP(0.00);
                this.setEFMP(0.00);
                this.setEFGO(-1.00);
               // JOptionPane.showMessageDialog(null, "Tag4ouMais1:");
            } 
        }
    }
    //Valores das métricas    
    //AP = quantidade de acertos na previsão do desempenho.

    public double AP(double ap) {

        return ap;
    }

    //QP = quantidade de problemas envolvidos na avaliação.
    public double QP(double qp) {
        return qp;
    }

    //tag
    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public String getTag4() {
        return tag4;
    }

    public void setTag4(String tag4) {
        this.tag4 = tag4;
    }   
    

    //Valores
    public double getEFGP() {
        return EFGP;
    }

    public void setEFGP(double EFGP) {
        this.EFGP = EFGP;
    }

    public double getEFMO() {
        return EFMO;
    }

    public void setEFMO(double EFMO) {
        this.EFMO = EFMO;
    }

    public double getEFGO() {
        return EFGO;
    }

    public void setEFGO(double EFGO) {
        this.EFGO = EFGO;
    }

    public double getEFMP() {
        return EFMP;
    }

    public void setEFMP(double EFMP) {
        this.EFMP = EFMP;
    }
}
