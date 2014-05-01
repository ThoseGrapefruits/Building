package main;

import interactive.Door;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import people.Me;
import view.Surface;
import view.View;
import boundaries.Floor;
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

		// Add barriers
		building.walls.add( new Wall( building, 750, 50, 390 + Constants.FLOOR_HEIGHT ) );
		building.walls.add( new Wall( building, 50, 50, 390 + Constants.FLOOR_HEIGHT ) );
		building.doors.add( new Door( building, 750, 440 + Constants.FLOOR_HEIGHT ) );
		building.doors.add( new Door( building, 50, 440 + Constants.FLOOR_HEIGHT ) );
		building.floors.add( new Floor( building, 50, 550, 500 ) );
		building.floors.add( new Floor( building, 50, 550, 500 ) );
		building.floors.add( new Floor( building, 50, 550 - Constants.FLOOR_DISTANCE, 500 ) );
		building.floors.add( new Floor( building, 50, 550 - Constants.FLOOR_DISTANCE * 2, 500 ) );
		building.floors.add( new Floor( building, 50, 550 - Constants.FLOOR_DISTANCE * 3, 500 ) );
		building.floors.add( new Floor( building, 50, 550 - Constants.FLOOR_DISTANCE * 4, 500 ) );

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
