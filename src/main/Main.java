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
		building.me = new Me( building, 100, 400 );
		building.walls.add( new Wall( building, 700, 360, 30 ) );
		building.walls.add( new Wall( building, 50, 360, 140 ) );
		building.doors.add( new Door( building, 700, 390 ) );
		building.floors.add( new Floor( building, 100, 500, 500 ) );
		building.addPerson( 100, 400 );

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
