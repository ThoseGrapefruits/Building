package interactive;

import java.awt.Graphics2D;

import main.Building;
import base.BuildingObject;
import base.Interactive;
import base.Visible;
import constants.Constants;

/**
 * A big metal box that goes up and down when you press buttons.
 * 
 * @author Logan Moore
 */
public class Elevator extends BuildingObject implements Interactive, Visible, Runnable
{
	/**
	 * List of floors above the given point where the elevator will also land (the floor index, not their location).
	 */
	int[] floors;

	/**
	 * <pre>BuildingObject</pre> currently riding in the elevator.
	 */
	public BuildingObject passenger;

	/**
	 * Destination floor
	 */
	int destinationFloor;

	/**
	 * Current floor;
	 */
	int currentFloor;

	/**
	 * Current height of elevator car
	 */
	double carHeight;

	public double getCarHeight()
	{
		return carHeight;
	}

	/**
	 * How open the doors of the elevator are.
	 */
	double doors = 1.0;

	/**
	 * Whether or not the elevator is open / opening.
	 */
	boolean open = false;

	/**
	 * Creates a new elevator object.
	 * 
	 * @param location is the pixel location of the bottom elevator.
	 * @param floors are the other floors that the elevator opens on.
	 */
	public Elevator( Building building, double x, double y, int[] floors )
	{
		super( building, x, y, Constants.ELEVATOR_WIDTH, Constants.ELEVATOR_CAR_HEIGHT );
		this.floors = floors;
		this.carHeight = y;
		for ( int floor : floors )
		{
			this.building.elevatorButtons
					.add( new ElevatorButton( this.building, x - 20, y + floor
							* Constants.FLOOR_DISTANCE + ( Constants.FLOOR_DISTANCE * 2 / 3 ),
							this, floor ) );
		}
		this.destinationFloor = floors[ 0 ];
	}

	/**
	 * Brings the elevator to the given floor. Called by <pre>ElevatorButton</pre>s.
	 * 
	 * @param floor is the floor the elevator is called from.
	 */
	public void call( ElevatorButton button )
	{
		this.building.elevatorButtons.get( this.currentFloor ).elevatorOnCurrentFloor = false;
		this.destinationFloor = button.floor;
		System.out.println( "Elevator called to floor " + this.destinationFloor + "." );
	}

	public void move( Building b )
	{
		int destinationFloorHeight = ( int ) ( this.y + this.destinationFloor
				* Constants.FLOOR_DISTANCE + Constants.FLOOR_HEIGHT );
		if ( destinationFloorHeight == ( int ) this.carHeight )
		{
			this.velocityY = 0;
			this.building.elevatorButtons.get( this.destinationFloor ).elevatorOnCurrentFloor = true;
			this.open = true;
			this.passenger = null;
			this.currentFloor = this.destinationFloor;
		}
		else if ( ( int ) this.carHeight > destinationFloorHeight
				&& this.velocityY > -Constants.ELEVATOR_MAX_VELOCITY )
		{
			this.velocityY -= 0.01;
			this.building.elevatorButtons.get( this.destinationFloor ).elevatorOnCurrentFloor = false;
			this.open = false;
		}
		else if ( ( int ) this.carHeight < destinationFloorHeight
				&& this.velocityY < Constants.ELEVATOR_MAX_VELOCITY )
		{
			this.velocityY += 0.01;
			this.building.elevatorButtons.get( this.destinationFloor ).elevatorOnCurrentFloor = false;
			this.open = false;
		}
		this.carHeight += this.velocityY;
	}

	@Override
	public void run()
	{
		while ( true )
		{
			if ( this.open && this.doors > 0.001 )
			{
				this.doors *= 0.9;
			}
			else if ( !this.open && this.doors < 0.999 )
			{
				this.doors *= 1.2;
			}
			try
			{
				Thread.sleep( Constants.TICK );
			}
			catch ( InterruptedException e )
			{
				e.printStackTrace();
			}
		}
	}

	@Override
	public void interact( BuildingObject interacter )
	{
		this.inUse = true;
		this.passenger = interacter;
		this.building.elevatorButtons.get( this.currentFloor ).elevatorOnCurrentFloor = false;
		this.destinationFloor = ( int ) ( Math.random() * this.floors.length );
		this.inUse = false;
	}

	@Override
	public void paint( Graphics2D g2d )
	{

		g2d.setColor( Constants.ELEVATOR_SHAFT_COLOR );
		for ( int floor : floors )
		{
			g2d.fillRect( ( int ) this.x, ( int ) this.y + Constants.FLOOR_HEIGHT * 2 + floor
					* Constants.FLOOR_DISTANCE, Constants.DOOR_OPEN_WIDTH * 2 + 4,
					Constants.ELEVATOR_CAR_HEIGHT );
		}

		g2d.setColor( Constants.ELEVATOR_CAR_COLOR );
		g2d.fillRect( ( int ) this.x, ( int ) this.carHeight + Constants.FLOOR_HEIGHT, this.width,
				this.height );

		g2d.setColor( Constants.ELEVATOR_DOOR_COLOR );
		for ( int floor : floors )
		{
			if ( floor == this.destinationFloor || floor == this.currentFloor )
			{
				g2d.fillRect( ( int ) this.x, ( int ) ( this.y + Constants.FLOOR_HEIGHT * 2 + floor
						* Constants.FLOOR_DISTANCE ),
						( int ) ( Constants.DOOR_OPEN_WIDTH * this.doors ),
						Constants.ELEVATOR_CAR_HEIGHT );
				g2d.fillRect(
						( int ) ( this.x + 4 + Constants.DOOR_OPEN_WIDTH * 2 - Constants.DOOR_OPEN_WIDTH
								* this.doors ), ( int ) this.y + Constants.FLOOR_HEIGHT * 2 + floor
								* Constants.FLOOR_DISTANCE,
						( int ) ( Constants.DOOR_OPEN_WIDTH * this.doors ),
						Constants.ELEVATOR_CAR_HEIGHT );
			}
			else
			{
				g2d.fillRect( ( int ) this.x, ( int ) this.y + Constants.FLOOR_HEIGHT * 2 + floor
						* Constants.FLOOR_DISTANCE, Constants.DOOR_OPEN_WIDTH,
						Constants.ELEVATOR_CAR_HEIGHT );
				g2d.fillRect( ( int ) this.x + Constants.DOOR_OPEN_WIDTH + 4, ( int ) this.y
						+ Constants.FLOOR_HEIGHT * 2 + floor * Constants.FLOOR_DISTANCE,
						Constants.DOOR_OPEN_WIDTH, Constants.ELEVATOR_CAR_HEIGHT );
			}
		}
	}
}
