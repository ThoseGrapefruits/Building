package base;

import java.awt.Graphics2D;

/**
 * Superclass to be extended by all visible objects.
 * 
 * @author Logan Moore
 * 
 */
public interface Visible
{
	boolean visible = true;

	int animationStep = 0;

	/**
	 * Draw the given object
	 * 
	 * @param g is the graphics outlet to use.
	 */
	public abstract void paint( Graphics2D g2d );
}
