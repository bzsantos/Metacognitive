package bz.metric.nac;

import bz.metric.control.MetricControl;
import bz.metric.model.Grupotags;
import bz.metric.dao.MetatagDAO;
import bz.metric.model.Metasort;
import bz.metric.model.Metatags;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Bruno Zolotareff dos Santos
 */
public class MetricNac extends MetricMethods {

    
    public double kmetrics(String tag) throws SQLException {
        this.mets(tag);

        return nac(kma(this.getAP(), this.getEFMO(), this.getEFMP(), this.getEFGO(), this.getEFGP(), this.getQP()), kmb(this.getEFMO(), this.getEFMP(), this.getEFGO(), this.getEFGP(), this.getQP()));
    }

    public double ka() throws SQLException {

        double ka = kma(this.getAP(), this.getEFMO(), this.getEFMP(), this.getEFGO(), this.getEFGP(), this.getQP());

        return ka;
    }
    
        public double kb() throws SQLException {

        double kb = kmb(this.getEFMO(), this.getEFMP(), this.getEFGO(), this.getEFGP(), this.getQP());
      
        return kb;
    }
    
        public double nc() throws SQLException {

        double ka = kma(this.getAP(), this.getEFMO(), this.getEFMP(), this.getEFGO(), this.getEFGP(), this.getQP());

        double kb = kmb(this.getEFMO(), this.getEFMP(), this.getEFGO(), this.getEFGP(), this.getQP());

        double nc = ka + kb;

        return nc;
    }
    
    
    public double kma(double AP, double EMO, double EMP, double EGO, double EGP, double QP) {

        double ka = ((AP * 1.00) + ((EMO + EMP) * -0.50) + ((EGO + EGP) * -1.00)) / QP;
        
        
      //  JOptionPane.showMessageDialog(null, "AP: " + AP + "EMO: " + EMO + "\n EMP" + EMP + "EGO: " + EGO + "\n EGP" + EGP + "\n QP" + QP);
        
//        JOptionPane.showMessageDialog(null, "kma: " + ka);
//        System.out.println("Valor kma: " + ka/2);

        return ka;
    }

    public double kmb(double EMO, double EMP, double EGO, double EGP, double QP) {

        double kb = ((EMO * 0.50) + (EMP * -0.50) + (EGO * 1.00) + (EGP * -1.00)) / QP;
        //JOptionPane.showMessageDialog(null, "kmb: " + kb);
        System.out.println("Valor kmb: " + kb);

        return kb;

    }

    public double nac(double kma, double kmb) {
        //retorna nac
        System.out.println("Valor nac: " + (kma + kmb));

        double totalkms = kma + kmb;

        return totalkms;
    }

    public void mets(String metadado) throws SQLException {

        this.setMetadado(metadado); //Valor que será atrubuído pelo usuário     
        
        MetricControl mc = new MetricControl();

        MetatagDAO grupodao = new MetatagDAO();
        
        //JOptionPane.showMessageDialog(null, "teste total: " + grupodao.contfolks());

        List<Metatags> resultado = grupodao.findGrupoTags();

       // List<Grupotags> grupolist = grupodao.listaGrupo(); 
        
        resultado.get(0).getTags();

        // JOptionPane.showMessageDialog(null,this.getAP());
        int tagone; //valor da folksonomia do 1º metadado
        int tagAp; //irá ter o valor do AP real
        int tagQp; //irá ter o valor do Qp real
        int tg;
        String tag; //primeiro metadado

        tagone = resultado.get(0).getFolks();        
//        tagone = mc.getNum();
//        tg = mc.getId();
        tag = resultado.get(0).getTags();
//         JOptionPane.showMessageDialog(null, "Tag: " + tag);
        //tagAp = tagone * tagone;
        tagAp = tagone;
//         JOptionPane.showMessageDialog(null, "teste total tagAp: " + tagAp);
        this.setAP(tagAp); //Coloca o valor da AP - Valor do metadado principal * principal - AP = quantidade de acertos na previsão do desempenho.       
        

        for (int i = 0; i < resultado.size(); i++) {
            //    for (int z = 0; z < grupolist.size(); z++) {

            String[] metacerto = new String[4];
            double[] meta = new double[4];

         
            metacerto[i] = resultado.get(i).getTags();
            meta[i] = resultado.get(i).getFolks();

            int sumtags = 0;
            int sumgrupo = 0;
            int resulQp = 0;
            
            sumgrupo = grupodao.contfolksmeta();
            //sumgrupo = grupodao.contfolks();
            JOptionPane.showMessageDialog(null, "teste total tagAp: " + tagAp);
            JOptionPane.showMessageDialog(null, "teste total sumgrupo: " + sumgrupo);
            
            tagQp = sumgrupo - tagAp;
            JOptionPane.showMessageDialog(null, "teste total tagQp: " + tagQp);
            
           // resulQp = tagAp / tagQp;
            
            resulQp =  tagQp / tagAp;
            
          // JOptionPane.showMessageDialog(null, "teste total resulQp: " + resulQp);
            this.setQP(resulQp);
            
            

//            for (Grupotags l : grupolist) {
//                //sumgrupo = sumgrupo + l.getFolks();
//                
//                
//                sumgrupo = grupodao.contfolks();
//                
////                JOptionPane.showMessageDialog(null, "teste total: " + grupodao.contfolks());
//
//                tagQp = sumgrupo - tagAp; //total de questões
//                
//               // JOptionPane.showMessageDialog(null, "teste total: " + tagQp);
//                
//                this.setQP(tagQp);
//            }

//            for (Metatags k : resultado) {
//                sumtags = sumtags + k.getFolks();
//                this.setQP(sumtags);
//            }
            
            
            
            this.setMetacerto(metacerto);//Pega as tags                
            this.setMeta(meta); //Pega folksonomia das tags

            if (this.getQP() == 0) {
                this.setQP(this.getAP());
            }
            //  }
            break;
        }
        this.AP(this.getAP());
        this.metametrics(this.getMetadado());
        
       // JOptionPane.showMessageDialog(null, "AP : " + this.getAP());
//        
//        JOptionPane.showMessageDialog(null, "metametrics : " + this.getMetadado());
        
        
//        JOptionPane.showMessageDialog(null, "nac: " + );

        JOptionPane.showMessageDialog(null, "Valor atribuido para EMO: " + this.getEFMO()
                + "\n Valor atribuido para EGO: " + this.getEFGO()
                + "\n Valor atribuido para EGP: " + this.getEFGP()
                + "\n Valor atribuido para EMP: " + this.getEFMP()
                + "\n Valor atribuido para NAC: " + (this.nc()));
    }
}
