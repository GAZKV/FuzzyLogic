/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

/**
 *
 * @author Kaloz
 */
public class Main {

    private double limite1;
    private double puntoCritico;
    private double limite2;

    public Main(double limite1, double puntoCritico, double limite2) {
        this.limite1 = limite1;
        this.puntoCritico = puntoCritico;
        this.limite2 = limite2;
    }

    double getGradoMembresia(double entrada) {
        /*
         * y=((y2 - y1)/(x2 - x1))(x - x1) + y1
         */
        if (entrada > getLimite1() && entrada < getLimite2()) {         
            return (entrada > getPuntoCritico())
                    ?
                    (((0 - 1) / (getLimite2() - getPuntoCritico())) * (entrada - getPuntoCritico()) + 1)
                    :
                    (((1 - 0) / (getPuntoCritico() - getLimite1())) * (entrada - getLimite1()) + 0);
        } else {
            return 0;
        }
    }

    public double getLimite1() {
        return limite1;
    }

    public double getPuntoCritico() {
        return puntoCritico;
    }

    public double getLimite2() {
        return limite2;
    }
    
    public static void main(String[] args) {
        double a=Double.parseDouble(javax.swing.JOptionPane.showInputDialog("valor de a"))
                ,b=Double.parseDouble(javax.swing.JOptionPane.showInputDialog("valor de b"))
                ,c=Double.parseDouble(javax.swing.JOptionPane.showInputDialog("valor de c"))
                ,x=Double.parseDouble(javax.swing.JOptionPane.showInputDialog("valor de x"));
        Main obj=new Main(a,b,c);
        System.out.println(obj.getGradoMembresia(x));
        javax.swing.JOptionPane.showMessageDialog(null, "El grado de membresia es: "+obj.getGradoMembresia(x));
    }
}
