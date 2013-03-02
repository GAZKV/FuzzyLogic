/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Class.Negocios;

/**
 *
 * @author Kaloz
 */
public class GraficaDifusa {

    private ConjuntoDifuso[] listaConjuntos;

    public GraficaDifusa() {
    }

    public GraficaDifusa(ConjuntoDifuso[] list) {
        this.listaConjuntos = list;
        System.arraycopy(list, 0, listaConjuntos, 0, list.length);
    }

    public ConjuntoDifuso getConjuntoDifuso(int index){
        return listaConjuntos[index];
    }
    
    public void insertarConjuntoDifuso(double firstPoint, double criticPoint, double lastPoint) {
        ConjuntoDifuso[] temp = new ConjuntoDifuso[listaConjuntos.length + 1];
        for (int i = 0; i < temp.length; i++) {
            if (i + 1 == temp.length) {
                temp[i].setFirstPoint(firstPoint);
                temp[i].setCriticPoint(criticPoint);
                temp[i].setLastPoint(lastPoint);
                temp[i].setType(ConjuntoDifuso.TRIANGLE);
            } else {
                temp[i] = listaConjuntos[i];
            }
        }
        listaConjuntos = temp;
    }

    public void insertarConjuntoDifuso(double firstPoint, double beforeFirstPoint, double afterLastPoint, double lastPoint) {
        ConjuntoDifuso[] temp = new ConjuntoDifuso[listaConjuntos.length + 1];
        for (int i = 0; i < temp.length; i++) {
            if (i + 1 == temp.length) {
                temp[i].setFirstPoint(firstPoint);
                temp[i].setBeforeFirstPoint(beforeFirstPoint);
                temp[i].setAfterLastPoint(afterLastPoint);
                temp[i].setLastPoint(lastPoint);
                temp[i].setType(ConjuntoDifuso.TRAPEZOIDAL);

            } else {
                temp[i] = listaConjuntos[i];
            }
        }
        listaConjuntos = temp;
    }

    public void insertarConjuntoDifuso(double criticPoint, double width) {
        ConjuntoDifuso[] temp = new ConjuntoDifuso[listaConjuntos.length + 1];
        for (int i = 0; i < temp.length; i++) {
            if (i + 1 == temp.length) {;
                temp[i].setCriticPoint(criticPoint);
                temp[i].setWidth(width);
                temp[i].setType(ConjuntoDifuso.GAUSSIAN);
            } else {
                temp[i] = listaConjuntos[i];
            }
        }
        listaConjuntos = temp;
    }

    public double[] getGradosDPertenencia(double Xpoint) {
        double[] salida = new double[listaConjuntos.length];
        for (int i = 0; i < salida.length; i++) {
            salida[i] = listaConjuntos[i].getMembershipGrade(Xpoint);
        }
        return salida;
    }
    
        
}
