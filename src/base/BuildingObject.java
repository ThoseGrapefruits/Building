package base;

import java.awt.Rectangle;

import main.Building;

/**
 * Superclass on which all objects in the building are based on. Includes
 * universal attributes applicable to any object.
 * 
 * @author Logan Moore
 */
public abstract class BuildingObject
{
	protected BuildingObject( Building building, int x, int y, int width, int height )
	{
		this.building = building;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	protected transient Building building;

	public Rectangle getBounds()
	{
		return new Rectangle( ( int ) this.x, ( int ) this.y, this.width, this.height );
	}

	/**
	 * Array of animation counts to be used by the individual object's paint
	 * function to keep track of the current frame.
	 */
	protected int[] animationStep = new int[ 3 ];

	/**
	 * Speed and direction of the current object in pixels per tick.
	 * Directions/signs follow the pixel coordinate conventions.
	 */
	public transient double velocityX = 0, velocityY = 0;

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
	public transient double x, y;

	/**
	 * Pixel dimensions of the object
	 */
	protected int width, height;

	/**
	 * Returns the width of the object in pixels
	 * 
	 * @return width of the object
	 */
	public int getWidth()
	{
		return this.width;
	}

	/**
	 * Returns the height of the object in pixels
	 * 
	 * @return height of the object
	 */
	public int getHeight()
	{
		return this.height;
	}

	/**
	 * Whether or not the given object is interactive. Overridden by the
	 * interactive class.
	 */
	protected boolean interactive = false;

	/**
	 * Returns whether or not the given object is interactive.
	 * 
	 * @return interactivity of the object
	 */
	public boolean isInteractive()
	{
		return interactive;
	}

	/**
	 * Whether or not the given object is being used by something else.
	 */
	protected boolean inUse = false;

	public boolean isInUse()
	{
		return inUse;
	}
}
