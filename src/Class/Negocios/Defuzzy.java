/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Class.Negocios;

/**
 *
 * @author Kaloz
 */
public class Defuzzy {

    double[] resultadosInferencia;
    double[] puntosGraph;
    int salida;
    GraficaDifusa grafica;

    public double[] getResultadosInferencia() {
        return resultadosInferencia;
    }

    public void setResultadosInferencia(double[] resultadosInferencia) {
        this.resultadosInferencia = resultadosInferencia;
    }

    //(n*5)-5
    // resultado,limite1,limite2,x1,x2
    public Defuzzy(GraficaDifusa grafica, double[] resultadosInferencia) {
        this.grafica = grafica;
        this.resultadosInferencia = resultadosInferencia;
        this.puntosGraph = new double[resultadosInferencia.length * 5];
    }

    public int getSalida() {
        return salida;
    }

    public double desfuzificar() {
        double resultadoSumatoria = 0, resultadoSumatoriaAreas = 0, temp_resSum = 0, temp_resSumAreas = 0, resultado;
        double puntoX1, puntoX2, firstPoint, lastPoint, gradoPertenencia;
        for (int i = 0; i < getResultadosInferencia().length; i++) {
            //      n=(i*5)-5
            //             r  l1 l2 x1 x2
            /* i=0 : i+1=1 0  1  2  3  4
             * i=1 : i+1=2 5  6  7  8  9
             * i=2 : i+1=3 10 11 12 13 14
             * i=3 : i+1=4 15 16 17 18 19 
             */
            /* 
             * n resultado,
             * n+1 limite1,
             * n+2 limite2,
             * n+3 x1,
             * n+4 x2
             */
            gradoPertenencia = getResultadosInferencia()[i];// Y
            firstPoint = grafica.getConjuntoDifuso(i).getFirstPoint();// limite 1
            lastPoint = grafica.getConjuntoDifuso(i).getLastPoint();// limite 2
            puntoX1 = /*X1{*/ calculaX(
                    /*y*/getResultadosInferencia()[i],
                    /*x1*/ grafica.getConjuntoDifuso(i).getFirstPoint(),
                    /*x2*/ grafica.getConjuntoDifuso(i).getCriticPoint(),
                    /*y1*/ 0,
                    /*y2*/ 1);// X1
            puntoX2 = /*X2{*/ calculaX(
                    /*y*/getResultadosInferencia()[i],
                    /*x1*/ grafica.getConjuntoDifuso(i).getCriticPoint(),
                    /*x2*/ grafica.getConjuntoDifuso(i).getLastPoint(),
                    /*y1*/ 1,
                    /*y2*/ 0);// X2

            temp_resSum = centroideTrapecio(
                    /*X1{*/puntoX1,
                    /*X2{*/ puntoX2,
                    /*limite1*/ firstPoint,
                    /*limite2*/ lastPoint,
                    /*Y*/ gradoPertenencia)
                    * areaTrapecio(
                    /*X1{*/puntoX1,
                    /*X2{*/ puntoX2,
                    /*limite1*/ firstPoint,
                    /*limite2*/ lastPoint,
                    /*Y*/ gradoPertenencia);

            temp_resSumAreas = areaTrapecio(
                    /*X1{*/puntoX1,
                    /*X2{*/ puntoX2,
                    /*limite1*/ firstPoint,
                    /*limite2*/ lastPoint,
                    /*Y*/ gradoPertenencia);

            resultadoSumatoria += (temp_resSum >= 0 && temp_resSum < 10000) ? temp_resSum : 0;
            resultadoSumatoriaAreas += (temp_resSumAreas >= 0 && temp_resSumAreas < 10000) ? temp_resSumAreas : 0;

        }

        //resultado del centroide
        resultado = resultadoSumatoria / resultadoSumatoriaAreas;

        for (int i = 0; i < getResultadosInferencia().length; i++) {// para saber que conjunto de la grafica defuzzificada fue en el que cayo el resultado del centroide
            if (resultado > grafica.getConjuntoDifuso(i).getBeforeFirstPoint() && resultado < grafica.getConjuntoDifuso(i).getLastPoint()) {
                salida = i;
            }
        }
        return resultado;
    }

    public double centroideTrapecio(double x1, double x2, double limite1, double limite2, double y) {
        double out = 0;
        double a_Tri1 = AreaTriangulo(x1 - limite1, y),
                c_Tri1 = CentroideTriangulo(x1 - limite1),
                a_Tri2 = AreaTriangulo(limite2 - x2, y),
                c_Tri2 = CentroideTriangulo(limite2 - x2),
                a_Rec = AreaRectangulo(x2 - x1, y),
                c_Rec = CentroideRectangulo(x2 - x1);
        out = ((a_Tri1 * (c_Tri1 + limite1)) + (a_Rec * (c_Rec + x1)) + (a_Tri2 * (c_Tri2 + x2))) / (a_Tri1 + a_Rec + a_Tri2);
        return out;
    }

    public double areaTrapecio(double x1, double x2, double limite1, double limite2, double y) {
        double out = 0;
        double a_Tri1 = AreaTriangulo(x1 - limite1, y),
                a_Tri2 = AreaTriangulo(limite2 - x2, y),
                a_Rec = AreaRectangulo(x2 - x1, y);
        out = (a_Tri1 + a_Rec + a_Tri2);
        return out;
    }

    public double AreaTriangulo(double base, double altura) {
        return (base * altura) / 2;
    }

    public double CentroideTriangulo(double base) {
        return (2 * base) / 3;
    }

    public double AreaRectangulo(double base, double altura) {
        return base * altura;
    }

    public double CentroideRectangulo(double base) {
        return base / 2;
    }

    //x=((y-y1)/((y2-y1)/(x2-x1)))+x1
    public double calculaX(double y, double x1, double x2, double y1, double y2) {
        return ((y - y1) / ((y2 - y1) / (x2 - x1))) + x1;
    }
}
