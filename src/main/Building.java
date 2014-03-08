package main;
import interactive.Door;
import interactive.Elevator;
import interactive.Light;
import interactive.LightSwitch;

import java.awt.EventQueue;
import java.util.ArrayList;

import view.View;
import boundaries.Floor;
import boundaries.Wall;

public class Building
{
	// Interaction
	ArrayList < Light > lights = new ArrayList < Light >();
	ArrayList < LightSwitch > lightSwitches = new ArrayList < LightSwitch >();
	ArrayList < Door > doors = new ArrayList < Door >();
	ArrayList < Elevator > elevators = new ArrayList < Elevator >();

	// Boundaries
	ArrayList < Wall > walls = new ArrayList < Wall >();
	ArrayList < Floor > floors = new ArrayList < Floor >();

	Building( int width )
	{
		EventQueue.invokeLater( new Runnable()
		{
			public void run()
			{
				try
				{
					View frame = new View();

					frame.setVisible( true );
				}
				catch ( Exception e )
				{
					e.printStackTrace();
				}
			}
		} );
	}

	void addLight( int x, int y )
	{

	}

	void addLightSwitch( int x, int y )
	{

	}

	/**
	 * Launch the application.
	 */
	public static void main( String[] args )
	{
		Building building = new Building( 700 );
	}
}
