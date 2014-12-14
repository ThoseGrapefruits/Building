package base;

import main.Building;

/**
 * Created by thosegrapefruits on 2014.12.13.
 */
public interface Moveable
{
	/**
	 * Move the given object.
	 */
	public abstract void move(Building b);

	/**
	 * Check if the given object can be moved on the X axis.
	 *
	 * @return whether the object can be moved on the X axis.
	 */
	public abstract boolean canMoveX(Building b);

	/**
	 * Check if the given object can be moved on the Y axis.
	 *
	 * @return whether the object can be moved on the Y axis.
	 */
	public abstract boolean canMoveY(Building b);
}
