package base;

public abstract class BuildingObject
{
	/**
	 * Visibility of the given object (whether or not it is to be drawn).
	 */
	protected boolean visible = false;

	public boolean isVisible()
	{
		return visible;
	}

	/**
	 * Pixel coordinates of the object.
	 */
	public int x, y;

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
}
