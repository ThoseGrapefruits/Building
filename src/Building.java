import interactable.Door;
import interactable.Elevator;
import interactable.Light;
import interactable.LightSwitch;

import java.awt.EventQueue;
import java.util.ArrayList;

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

	Building()
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

	void addLight()
	{

	}

	void addLightSwitch()
	{

	}

	/**
	 * Launch the application.
	 */
	public static void main( String[] args )
	{
		Building building = new Building();
	}
}
