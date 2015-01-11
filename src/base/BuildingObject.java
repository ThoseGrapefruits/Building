package base;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import main.Building;

/**
 * Superclass on which all objects in the building are based on. Includes
 * universal attributes applicable to any object.
 * 
 * @author Logan Moore
 */
public abstract class BuildingObject
{
	/**
	 * Creates a new <pre>BuildingObject</pre> 
	 * 
	 * @param building is the building in which the object exists.
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	protected BuildingObject( Building building, double x, double y, int width, int height )
	{
		this.building = building;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * Hitboxes for the given object. Initialized in the <code>Moveable</code> interface.
	 */
	public ArrayList<Rectangle> hitboxes;

	/**
	 * The building in which the current object exists.
	 * 
	 * This is used so that given objects can interact with the building itself easier, and have
	 * access to the lists of objects in the <pre>Building</pre>.
	 */
	protected transient Building building;

	/**
	 * Whether or not to draw the boundaries of the current object.
	 */
	public boolean drawBounds = false;

	public void drawBounds( Graphics2D g2d )
	{
		if ( this.drawBounds )
		{
			Rectangle bounds = this.getBounds();
			g2d.setColor( Color.CYAN );
			g2d.drawRect( bounds.x, bounds.y, bounds.width, bounds.height );
		}
	}

	/**
	 * Generates and returns the bounds of the current object using the <pre>x</pre>, </pre>y</pre>,
	 * <pre>width</pre>, and <pre>height</pre> of the object.
	 * 
	 * @return the bounds of an object, of type <pre>Rectangle</pre>.
	 */
	public Rectangle getBounds()
	{
		return new Rectangle( ( int ) this.x, ( int ) this.y, this.width, this.height );
	}

	/**
	 * Returns whether the current object intersects with a given set of bounds.
	 * @param r is the given rectangle to check for intersection.
	 * @return true if intersection is found, false otherwise.
	 */
	public boolean intersects( Rectangle r )
	{
		return this.getBounds().intersects( r );
	}

	/**
	 * Array of animation counts to be used by the individual object's paint
	 * function to keep track of the current frame.
	 */
	protected int[] animationStep = new int[ 16 ];

	/**
	 * Speed and direction of the current object in pixels per tick.
	 * Directions/signs follow the pixel coordinate conventions.
	 */
	public transient double velocityX = 0, velocityY = 0;

	/**
	 * Visibility of the given object (whether or not it is to be drawn).
	 */
	protected final boolean visible = false;

	/**
	 * Visibility of the object (whether or not it is to be drawn).
	 * 
	 * @return if the object is visible or not.
	 */
	public boolean isVisible()
	{
		return visible;
	}

	/**
	 * Whether the object can be walked through
	 */
	boolean passable = true;

	/**
	 * If the object can be walked "through" by other objects (such as people) or if it collides.
	 * 
	 * @return if the object can be passed.
	 */
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
	 * @return <pre>interactive</pre>
	 */
	public boolean isInteractive()
	{
		return interactive;
	}

	/**
	 * Whether or not the given object is being used by something else.
	 */
	protected boolean inUse = false;

	/**
	 * Whether or not the given object is being used by something else.
	 * 
	 * @return inUse
	 */
	public boolean isInUse()
	{
		return inUse;
	}

	@Override
	public String toString()
	{
		return this.getClass().toString() + "{x=" + this.x + ", y=" + this.y + ", w="
				+ this.getWidth() + ", h=" + this.getHeight() + ", vX="
				+ this.velocityX + ", vY="+ this.velocityY + "}";
	}
}
