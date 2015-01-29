package interactive;

import base.BuildingObject;
import base.Interactive;
import base.Visible;
import constants.Constants;
import main.Building;

import java.awt.Graphics2D;

/**
 * A big metal box that goes up and down when you press buttons.
 *
 * @author Logan Moore
 */
public class Elevator extends BuildingObject implements Interactive, Visible, Runnable {
	/**
	 * <pre>BuildingObject</pre> currently riding in the elevator.
	 */
	public BuildingObject passenger;
	/**
	 * List of floors above the given point where the elevator will also land (the floor index, not their location).
	 */
	int[] floors;
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
	 * @param floors are the other floors that the elevator opens on.
	 */
	public Elevator( Building building, double x, double y, int[] floors ) {
		super( building, x, y, Constants.ELEVATOR_WIDTH, Constants.ELEVATOR_CAR_HEIGHT );
		this.floors = floors;
		carHeight = y;
		for ( int floor : floors ) {
			building.elevatorButtons
					.add( new ElevatorButton( building, x - 20, y + floor
							* Constants.FLOOR_DISTANCE + ( Constants.FLOOR_DISTANCE * 2 / 3 ),
							this, floor ) );
		}
		destinationFloor = floors[ 0 ];
	}

	public double getCarHeight() {
		return carHeight;
	}

	/**
	 * Brings the elevator to the given floor. Called by <pre>ElevatorButton</pre>s.
	 *
	 */
	public void call( ElevatorButton button ) {
		building.elevatorButtons.get( currentFloor ).elevatorOnCurrentFloor = false;
		destinationFloor = button.floor;
		System.out.println( "Elevator called to floor " + destinationFloor + "." );
	}

	public void move( Building b ) {
		int destinationFloorHeight = ( int ) ( y + destinationFloor
				* Constants.FLOOR_DISTANCE + Constants.FLOOR_HEIGHT );
		if ( destinationFloorHeight == ( int ) carHeight ) {
			velocityY = 0;
			building.elevatorButtons
					.get( destinationFloor ).elevatorOnCurrentFloor = true;
			open = true;
			passenger = null;
			currentFloor = destinationFloor;
		}
		else if ( ( int ) carHeight > destinationFloorHeight
				&& velocityY > -Constants.ELEVATOR_MAX_VELOCITY ) {
			velocityY -= 0.01;
			building.elevatorButtons
					.get( destinationFloor ).elevatorOnCurrentFloor = false;
			open = false;
		}
		else if ( ( int ) carHeight < destinationFloorHeight
				&& velocityY < Constants.ELEVATOR_MAX_VELOCITY ) {
			velocityY += 0.01;
			building.elevatorButtons
					.get( destinationFloor ).elevatorOnCurrentFloor = false;
			open = false;
		}
		carHeight += velocityY;
	}

	@Override
	public void run() {
		while ( true ) {
			if ( open && doors > 0.001 ) {
				doors *= 0.9;
			}
			else if ( !open && doors < 0.999 ) {
				doors *= 1.2;
			}
			try {
				Thread.sleep( Constants.TICK );
			}
			catch ( InterruptedException e ) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void interact( BuildingObject interacter ) {
		inUse = true;
		passenger = interacter;
		building.elevatorButtons.get( currentFloor ).elevatorOnCurrentFloor = false;
		destinationFloor = ( int ) ( Math.random() * floors.length );
		inUse = false;
	}

	@Override
	public void paint( Graphics2D g2d ) {

		g2d.setColor( Constants.ELEVATOR_SHAFT_COLOR );
		for ( int floor : floors ) {
			g2d.fillRect( ( int ) x, ( int ) y + Constants.FLOOR_HEIGHT * 2 + floor
							* Constants.FLOOR_DISTANCE, Constants.DOOR_OPEN_WIDTH * 2 + 4,
					Constants.ELEVATOR_CAR_HEIGHT );
		}

		g2d.setColor( Constants.ELEVATOR_CAR_COLOR );
		g2d.fillRect( ( int ) x, ( int ) carHeight + Constants.FLOOR_HEIGHT, width,
				height );

		g2d.setColor( Constants.ELEVATOR_DOOR_COLOR );
		for ( int floor : floors ) {
			if ( floor == destinationFloor || floor == currentFloor ) {
				g2d.fillRect( ( int ) x, ( int ) ( y + Constants.FLOOR_HEIGHT * 2 + floor
								* Constants.FLOOR_DISTANCE ),
						( int ) ( Constants.DOOR_OPEN_WIDTH * doors ),
						Constants.ELEVATOR_CAR_HEIGHT );
				g2d.fillRect(
						( int ) ( x + 4 + Constants.DOOR_OPEN_WIDTH * 2
								- Constants.DOOR_OPEN_WIDTH
								* doors ), ( int ) y + Constants.FLOOR_HEIGHT * 2 + floor
								* Constants.FLOOR_DISTANCE,
						( int ) ( Constants.DOOR_OPEN_WIDTH * doors ),
						Constants.ELEVATOR_CAR_HEIGHT );
			}
			else {
				g2d.fillRect( ( int ) x, ( int ) y + Constants.FLOOR_HEIGHT * 2 + floor
								* Constants.FLOOR_DISTANCE, Constants.DOOR_OPEN_WIDTH,
						Constants.ELEVATOR_CAR_HEIGHT );
				g2d.fillRect( ( int ) x + Constants.DOOR_OPEN_WIDTH + 4, ( int ) y
								+ Constants.FLOOR_HEIGHT * 2 + floor * Constants.FLOOR_DISTANCE,
						Constants.DOOR_OPEN_WIDTH, Constants.ELEVATOR_CAR_HEIGHT );
			}
		}
	}
}
