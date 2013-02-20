/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Class.Negocios;

import java.util.ArrayList;

/**
 *
 * @author Kaloz
 */

public class ConjuntoDifuso {
    ArrayList h;
    private double width;
    private double firstPoint;
    private double beforeFirstPoint;
    private double criticPoint;
    private double afterLastPoint;
    private double lastPoint;
    private int type;
    private final int TRIANGLE = 0, TRAPEZOIDAL = 1, GAUSSIAN = 2;

    public ConjuntoDifuso() {
    }

    public ConjuntoDifuso(double firstPoint, double criticPoint, double lastPoint) {
        this.firstPoint = firstPoint;
        this.criticPoint = criticPoint;
        this.lastPoint = lastPoint;
        type = TRIANGLE;
    }

    public ConjuntoDifuso(double firstPoint, double beforeFirstPoint, double afterLastPoint, double lastPoint) {
        this.firstPoint = firstPoint;
        this.beforeFirstPoint = beforeFirstPoint;
        this.afterLastPoint = afterLastPoint;
        this.lastPoint = lastPoint;
        type = TRAPEZOIDAL;
    }

    public ConjuntoDifuso(double criticPoint, double width) {
        this.width = width;
        this.criticPoint = criticPoint;
        type = GAUSSIAN;
    }

    public double getMembershipGrade(double XPoint) {
        switch (getType()) {
            case TRIANGLE:
                return membershipGradeTriangle(XPoint);
            case TRAPEZOIDAL:
                return membershipGradeTrapezoidal(XPoint);
            case GAUSSIAN:
                return membershipGradeGaussian(XPoint);
            default:
                return 0;
        }
    }

    private double membershipGradeTriangle(double XPoint) {
        if (XPoint > getFirstPoint() && XPoint < getLastPoint()) {
            if (XPoint > getCriticPoint()) {
                return (((0 - 1) / (getLastPoint() - getCriticPoint())) * (XPoint - getCriticPoint()) + 1);
            } else if (XPoint < getCriticPoint()) {
                return (((1 - 0) / (getCriticPoint() - getFirstPoint())) * (XPoint - getFirstPoint()) + 0);
            } else {
                return 1;
            }
        } else {
            return 0;
        }
    }

    private double membershipGradeTrapezoidal(double XPoint) {
        if (XPoint > getFirstPoint() && XPoint < getLastPoint()) {
            if (XPoint < getBeforeFirstPoint()) {
                return (((1 - 0) / (getCriticPoint() - getFirstPoint())) * (XPoint - getFirstPoint()) + 0);
            } else if (XPoint > getAfterLastPoint()) {
                return (((0 - 1) / (getLastPoint() - getCriticPoint())) * (XPoint - getCriticPoint()) + 1);
            } else if (XPoint > getBeforeFirstPoint() && XPoint < getAfterLastPoint()){
                return 1;
            }
        }
        return 0;
    }

    private double membershipGradeGaussian(double XPoint) {
        return 0;
    }
    
    public boolean equals(ConjuntoDifuso other){
        return (new ConjuntoDifusoComparador().Equals(this, other));
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getFirstPoint() {
        return firstPoint;
    }

    public void setFirstPoint(double firstPoint) {
        this.firstPoint = firstPoint;
    }

    public double getBeforeFirstPoint() {
        return beforeFirstPoint;
    }

    public void setBeforeFirstPoint(double beforeFirstPoint) {
        this.beforeFirstPoint = beforeFirstPoint;
    }

    public double getCriticPoint() {
        return criticPoint;
    }

    public void setCriticPoint(double criticPoint) {
        this.criticPoint = criticPoint;
    }

    public double getAfterLastPoint() {
        return afterLastPoint;
    }

    public void setAfterLastPoint(double afterLastPoint) {
        this.afterLastPoint = afterLastPoint;
    }

    public double getLastPoint() {
        return lastPoint;
    }

    public void setLastPoint(double lastPoint) {
        this.lastPoint = lastPoint;
    }

    public int getType() {
        return type;
    }
}
