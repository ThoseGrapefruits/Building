package base;

public abstract class BuildingObject
{
	protected BuildingObject( int x, int y, int width, int height )
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * Speed and direction of the current object. A positive velocity is up for y and to the right for x.
	 */
	protected transient int velocityX = 0;
	protected transient int velocityY = 0;

	public int getVelocityX()
	{
		return velocityX;
	}

	public int getVelocityY()
	{
		return velocityY;
	}

	/**
	 * Visibility of the given object (whether or not it is to be drawn).
	 */
	protected final boolean visible = false;

	public boolean isVisible()
	{
		return visible;
	}

	/**
	 * Whether the object can be walked through
	 */
	boolean passable = true;

	public boolean isPassable()
	{
		return passable;
	}

	/**
	 * Pixel coordinates of the object.
	 */
	protected transient int x, y;

	/**
	 * Pixel dimensions of the object
	 */
	protected int width, height;

	public int getWidth()
	{
		return this.width;
	}

	public int getHeight()
	{
		return this.height;
	}

	protected boolean interactive = false;

	public boolean isInteractive()
	{
		return interactive;
	}

	protected boolean inUse = false;

	public boolean isInUse()
	{
		return inUse;
	}
}
