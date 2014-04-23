package main;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import boundaries.Wall;
import people.Me;
import view.Surface;
import view.View;
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
		building.people.add( new Me( 100, 400 ) );
		building.people.add( new Me( 400, 400 ) );
		building.walls.add( new Wall( 500, 360, 140 ) );
		building.walls.add( new Wall( 50, 360, 140 ) );

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
