package interactive;

/**
 * Superclass to be extended by all interactive objects like LightSwitch, Elevator, Door, etc.
 * 
 * @author Logan Moore
 */
public class Interactive implements Runnable
{
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
