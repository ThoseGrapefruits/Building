package base;

import java.awt.*;

/**
 * Interface to be extended by all visible objects.
 *
 * @author Logan Moore
 */
public interface Visible {

    /**
     * Draw the given object
     *
     * @param g is the graphics outlet to use.
     */
    public abstract void paint(Graphics2D g2d);
}
