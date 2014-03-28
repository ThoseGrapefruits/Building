package interactive;

public class Door extends Interactive
{
	public boolean open = false;

	public void open()
	{
		open = true;
	}

	public void close()
	{
		open = false;
	}

	@Override
	public void run()
	{
	}

	public void interact()
	{
		this.inUse = true;
		// TODO
		System.out.println( "TODO Door interact()" );
		this.inUse = false;
	}
}
