package interactive;

/**
 * Superclass to be used by all interactable objects like LightSwitch, Elevator, Door, etc.
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
}
