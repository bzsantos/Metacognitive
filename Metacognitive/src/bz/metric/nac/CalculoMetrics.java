package bz.metric.nac;

import bz.metric.nac.MetricNac;
import javax.swing.JOptionPane;

/**
 *
 * @author bzsantos
 */
public class CalculoMetrics extends MetricNac {
    
    public double kmetrics(){
        
        //kma(double AP, double EMO, double EMP, double EGO, double EGP, double QP, double ka){
       // kma(1.0, this.getEFMO(), this.getEMP(), this.getEFGO(), this.getEFGP(),1.0, 0);
        
        //kmb(double EMO, double EMP, double EGO, double EGP, double QP, double kb){
       // kmb(this.getEFMO(), this.getEMP(), this.getEFGO(), this.getEFGP(), 1.0, 0);
       
//       JOptionPane.showMessageDialog(null, "Resultado kma: " + kma(this.getAP(), this.getEFMO(), this.getEMP(), this.getEFGO(), this.getEFGP(),25));
//       JOptionPane.showMessageDialog(null,"Resultado kmb: " + kmb(this.getEFMO(), this.getEMP(), this.getEFGO(), this.getEFGP(), this.getQP()));
        
               
       return nac(kma(this.getAP(), this.getEFMO(), this.getEMP(), this.getEFGO(), this.getEFGP(),25), kmb(this.getEFMO(), this.getEMP(), this.getEFGO(), this.getEFGP(), this.getQP()));
               
        
    }
    
    
    
}
