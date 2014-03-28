package interactive;

/**
 * Superclass to be extended by all interactive objects like LightSwitch, Elevator, Door, etc.
 * 
 * @author Logan Moore
 */
public class Interactive implements Runnable
{
	/**
	 * Pixel location of the interactive object.
	 */
	int x = 0;
	int y = 0;

	private boolean interactive = true;

	boolean isInteractive()
	{
		return interactive;
	}

	public boolean inUse = false;

	@Override
	public void run()
	{
	}

	public void interact()
	{
		System.out
				.println( "Something has interacted with an interactive object with no interact() function." );
	}
}
