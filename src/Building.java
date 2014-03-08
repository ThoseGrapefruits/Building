import java.awt.EventQueue;
import java.util.ArrayList;

import Interactable.Light;
import Interactable.LightSwitch;
import Interactable.Door;
import Interactable.Elevator;

public class Building
{
	ArrayList < Light > lights = new ArrayList < Light >();
	ArrayList < LightSwitch > lightSwitches = new ArrayList < LightSwitch >();

	/**
	 * Launch the application.
	 */
	public static void main( String[] args )
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
}
