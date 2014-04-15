package main;

// import java.awt.image.BufferStrategy;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

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
				Timer timer = new Timer( 10, panel );
				timer.setInitialDelay( 0 );
				timer.start();
			}
		} );
	}
}
