package interactive;

import java.awt.Graphics2D;

import main.Building;
import base.BuildingObject;
import base.Interactive;
import base.Visible;
import constants.Constants;

public class Elevator extends BuildingObject implements Interactive, Visible, Runnable
{
	/**
	 * List of floors above the given point where the elevator will also land (the floor index, not their location).
	 */
	int[] floors;

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
	int carHeight = 0;

	/**
	 * Creates a new elevator object.
	 * 
	 * @param location is the pixel location of the bottom elevator.
	 * @param floors are the other floors that the elevator opens on.
	 */
	Elevator( int x, int y, int[] floors )
	{
		super( x, y, Constants.ELEVATOR_WIDTH, Constants.ELEVATOR_HEIGHT );
		this.floors = floors;
	}

	public void call( int floor )
	{
		for ( int i : this.floors )
		{
			if ( i == floor )
			{
				this.destinationFloor = floor;
			}
		}
	}

	public void move( Building b )
	{
		this.y += this.velocityY;
	}

	@Override
	public void run()
	{
		// TODO probably overhaul this.
		while ( true )
		{
			try
			{
				Thread.sleep( 1000 );
				while ( carHeight * Constants.FLOOR_DISTANCE != destinationFloor
						* Constants.FLOOR_DISTANCE )
				{
					if ( currentFloor < destinationFloor )
					{
						while ( velocityY < Constants.ELEVATOR_MAX_VELOCITY )
						{
							velocityY++;
						}
					}
					else if ( currentFloor > destinationFloor )
					{
						while ( velocityY < Constants.ELEVATOR_MAX_VELOCITY )
						{
							velocityY--;
						}
					}
					Thread.sleep( 5 );
				}
				this.currentFloor = carHeight / constants.Constants.FLOOR_DISTANCE;
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

	@Override
	public void interact( BuildingObject interacter )
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void paint( Graphics2D g2d )
	{
		// TODO Auto-generated method stub

	}
}
