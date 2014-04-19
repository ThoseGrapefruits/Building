package main;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import constants.Constants;
import people.Me;
import view.View;
import view.Surface;

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
		final Me me = new Me( 100, 100 );
		building.people.add( me );

		// Create the visuals
		SwingUtilities.invokeLater( new Runnable()
		{
			@Override
			public void run()
			{
				Surface panel = new Surface( building );
				View view = new View( panel );
				// view.createBufferStrategy( 2 );
				view.setVisible( true );
				// view.addKeyListener( panel );
				Timer timer = new Timer( Constants.TICK, panel );
				timer.setInitialDelay( 0 );
				timer.start();
			}
		} );
	}
}
