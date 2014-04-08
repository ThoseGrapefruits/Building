package interactive;

import base.Visible;

/**
 * Superclass to be extended by all interactive objects like LightSwitch, Elevator, Door, etc.
 * 
 * @author Logan Moore
 */
public class Interactive extends Visible implements Runnable
{
	/**
	 * Pixel location of the interactive object.
	 */
	int x = 0, y = 0;

	protected boolean interactive = true;

	public boolean inUse = false;

	@Override
	public void run()
	{
	}

	public void interact()
	{
		System.out.println( "Interactive object with no interact() function." );
	}
}
