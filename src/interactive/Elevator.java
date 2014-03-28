package interactive;

import constants.Constants;

public class Elevator extends Interactive
{
	/**
	 * List of floors above the given point where the elevator will also land.
	 */
	int[] otherFloors;

	/**
	 * Destination floor
	 */
	int destinationFloor = 0;

	/**
	 * Current floor
	 */
	int currentFloor = 0;

	/**
	 * Current height of elevator car
	 */
	int currentHeight = 0;

	/**
	 * Creates a new elevator object.
	 * 
	 * @param location is the pixel location of the bottom elevator.
	 * @param otherFloors are the other floors that the elevator opens on.
	 */
	Elevator( int x, int y, int[] otherFloors )
	{
		this.x = x;
		this.y = y;
		this.otherFloors = otherFloors;
	}

	public boolean goToFloor( int floor )
	{
		for ( int i : otherFloors )
		{
			if ( i == floor )
			{
				this.destinationFloor = floor;
				return true;
			}
		}
		return false;
	}

	@Override
	public void run()
	{
		while ( true )
		{
			try
			{
				Thread.sleep( 1000 );
				while ( currentHeight * Constants.FLOOR_HEIGHT != destinationFloor
						* Constants.FLOOR_HEIGHT )
				{
					if ( currentFloor < destinationFloor )
					{
						currentHeight++;
					}
					else if ( currentFloor > destinationFloor )
					{
						currentHeight++;
					}
					Thread.sleep( 5 );
				}
				this.currentFloor = currentHeight / constants.Constants.FLOOR_HEIGHT;
			}
			catch ( InterruptedException e )
			{
				e.printStackTrace();
			}
		}
	}

	public void interact( int floor )
	{
		this.inUse = true;
		this.destinationFloor = floor;
		this.inUse = false;
	}
}
