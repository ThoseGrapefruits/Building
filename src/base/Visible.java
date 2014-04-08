package base;

import java.awt.Graphics;

/**
 * Superclass to be extended by all visible objects.
 * 
 * @author Logan Moore
 * 
 */
public abstract class Visible
{
	protected boolean visible = true;

	/**
	 * Draw the given object
	 * 
	 * @param g is the graphics outlet to use.
	 */
	public abstract void paint( Graphics g );
}
