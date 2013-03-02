/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import Class.Negocios.ConjuntoDifuso;
import Class.Negocios.Defuzzy;
import Class.Negocios.GraficaDifusa;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kaloz
 */
public class MainComplete {

    static double tiempo = 0;

    public static void iniciaHilo() {
        Thread temp = new Thread() {
            @Override
            public void run() {
                while (tiempo < 30) {
                    tiempo += .1;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainComplete.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        ConjuntoDifuso[] entradaSeg = new ConjuntoDifuso[]{
            new ConjuntoDifuso(0   , 4   , 7.7 ),
            new ConjuntoDifuso(1.4 , 6.4 , 10.5),
            new ConjuntoDifuso(4.2 , 9.2 , 14  ),
            new ConjuntoDifuso(9   , 13.3, 19.2),
            new ConjuntoDifuso(16.6, 20  , 21.8),
            new ConjuntoDifuso(20.5, 24  , 27  ),
            new ConjuntoDifuso(27  , 28  , 30  )
        };
        final GraficaDifusa entrada = new GraficaDifusa(entradaSeg);

        ConjuntoDifuso[] salidaKm = new ConjuntoDifuso[]{
            new ConjuntoDifuso(0  , 10.5 , 30  ),
            new ConjuntoDifuso(10 , 36   , 75  ),
            new ConjuntoDifuso(30 , 50   , 70  ),
            new ConjuntoDifuso(65 , 90   , 120),
            new ConjuntoDifuso(110, 120  , 130),
            new ConjuntoDifuso(125, 139.5, 150),
            new ConjuntoDifuso(140, 158  , 160)
        };
        final GraficaDifusa salida = new GraficaDifusa(salidaKm);


        Thread hilo = new Thread() {
            @Override
            public void run() {
                double[] gradosDePertenencia = null;
                double temp_tiempo = tiempo;
                while (tiempo <= 30) {
                    if (tiempo != temp_tiempo) {
                        gradosDePertenencia = entrada.getGradosDPertenencia(tiempo);
                        System.out.println((int) new Defuzzy(salida, gradosDePertenencia).desfuzificar());
                        temp_tiempo = tiempo;
                    }
                }
            }
        };
        temp.start();
        hilo.start();
    }

    public static void main(String[] args) {
        iniciaHilo();
//        ConjuntoDifuso[] lista = new ConjuntoDifuso[]{
//            new ConjuntoDifuso(0, 4, 7.7),
//            new ConjuntoDifuso(1.4, 6.4, 10.5),
//            new ConjuntoDifuso(4.2, 9.2, 14),
//            new ConjuntoDifuso(9, 13.3, 19.2),
//            new ConjuntoDifuso(16.6, 20, 21.8),
//            new ConjuntoDifuso(20.5, 24, 27),
//            new ConjuntoDifuso(27, 28, 29.6)
//        };
//        GraficaDifusa obj = new GraficaDifusa(lista);
//        double[] gradosDePertenencia = obj.getGradosDPertenencia(0);
//        for (int i = 0; i < gradosDePertenencia.length; i++) {
//            System.out.print(gradosDePertenencia[i] + ", ");
//        }
    }
}
