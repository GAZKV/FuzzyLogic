/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Class.Negocios;

/**
 *
 * @author Kaloz
 */
public class ConjuntoDifusoComparador {
    public boolean Equals(ConjuntoDifuso x, ConjuntoDifuso y) {
        return (x.getWidth() == y.getWidth()
                && x.getAfterLastPoint() == y.getAfterLastPoint()
                && x.getBeforeFirstPoint() == y.getBeforeFirstPoint()
                && x.getFirstPoint() == y.getFirstPoint()
                && x.getLastPoint() == y.getLastPoint()
                && x.getType() == y.getType());
    }
}
