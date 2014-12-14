package base;

/**
 * Hitbox to be used for collision detection
 */
public class HitBox
{
	/**
	 * Types of hitboxes. Determines the behavior of the <code>HitBox</code>.
	 */
	public boolean RECTANGLE = true, CIRCLE = false;

	/**
	 * Type of the <code>HitBox</code>.
	 */
	private boolean type;

	/**
	 * Returns the type of the <code>HitBox</code>.
	 * @return the type of the <code>HitBox</code>.
	 */
	public boolean getType()
	{
		return this.type;
	}
}
