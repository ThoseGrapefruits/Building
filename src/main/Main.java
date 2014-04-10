package main;

import javax.swing.SwingUtilities;

import view.View;

public class Main
{
	/**
	 * Launch the application.
	 */
	public static void main( String[] args )
	{
		SwingUtilities.invokeLater( new Runnable()
		{
			@Override
			public void run()
			{
				View sk = new View();
				sk.setVisible( true );
			}
		} );
	}
}
