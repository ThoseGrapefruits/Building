package interactable;

public class Elevator implements Runnable
{
	/**
	 * Pixel location for the bottom level elevator.
	 */
	int[] location =
	{ 0, 0 };

	int[] otherFloors;

	/**
	 * Creates a new elevator object.
	 * 
	 * @param location is the pixel location of the bottom elevator.
	 * @param otherFloors are the other floors that the elevator opens on.
	 */
	Elevator( int[] location, int[] otherFloors )
	{
		this.location = location;
		this.otherFloors = otherFloors;
	}

	@Override
	public void run()
	{
		while ( true )
		{

			try
			{
				Thread.sleep( 1000 );
			}
			catch ( InterruptedException e )
			{
				e.printStackTrace();
			}
		}

	}
}
