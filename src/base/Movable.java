package base;

import main.Building;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Interface to be extended by any non-physically-static <code>BuildingObject</code>s.
 *
 * @author Logan Moore
 */
public interface Movable {
	/**
	 * Hitboxes for the given object.
	 */
	public ArrayList<Rectangle> hitboxes = new ArrayList<Rectangle>();

	/**
	 * Move the given object.
	 */
	public abstract void move( Building b );

	/**
	 * Check if the given object can be moved on the X axis.
	 *
	 * @return whether the object can be moved on the X axis.
	 */
	public abstract boolean canMoveX( Building b );

	/**
	 * Check if the given object can be moved on the Y axis.
	 *
	 * @return whether the object can be moved on the Y axis.
	 */
	public abstract boolean canMoveY( Building b );
}
