package main;

import interactive.Door;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import people.Me;
import people.Tai;
import view.Surface;
import view.View;
import boundaries.Wall;
import constants.Constants;

/**
 * Main controller for the program.
 * 
 * @author Logan Moore
 *
 */
public class Main
{
	/**
	 * Launch the application.
	 */
	public static void main( String[] args )
	{
		// Create a new building
		final Building building = new Building();

		// Add people
		building.me = new Me( building, 100, 450 );
		building.addPerson( 500, 450 );
		building.addPerson( new Tai( building, 400, 450 ) );

		// Outer doors/walls
		building.walls.add( new Wall( building, 750, 50, 390 ) );
		building.walls.add( new Wall( building, 50, 50, 390 ) );
		building.doors.add( new Door( building, 750, 440 ) );
		building.doors.add( new Door( building, 50, 440 ) );

		// Inner doors/walls
		building.doors.add( new Door( building, 350, 440 ) );
		building.walls.add( new Wall( building, 350, 440 - Constants.FLOOR_HEIGHT,
				Constants.FLOOR_DISTANCE - Constants.DOOR_HEIGHT - Constants.FLOOR_HEIGHT ) );

		// Floors
		building.addFloor( 50, 550, 800 );
		building.addFloor( 50, 550 - Constants.FLOOR_DISTANCE, 700 );
		building.addFloor( 50, 550 - Constants.FLOOR_DISTANCE * 2, 700 );
		building.addFloor( 50, 550 - Constants.FLOOR_DISTANCE * 3, 700 );
		building.addFloor( 50, 550 - Constants.FLOOR_DISTANCE * 4, 700 );

		// Lights
		building.addLightAndSwitch( 100, 420, 100, 450 );

		// Elevators
		building.addElevator( 200.0, 50.0, new int[]
		{ 0, 1, 2, 3 } );

		// Create the visuals
		SwingUtilities.invokeLater( new Runnable()
		{
			@Override
			public void run()
			{
				Surface panel = new Surface( building );
				View view = new View( panel );
				view.setVisible( true );
				Timer timer = new Timer( Constants.TICK, panel );
				timer.setInitialDelay( 0 );
				timer.start();
			}
		} );
	}
}
