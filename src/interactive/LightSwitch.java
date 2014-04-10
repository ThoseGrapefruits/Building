package interactive;

import java.awt.Graphics2D;

import base.BuildingObject;
import base.Visible;
import constants.Constants;

public class LightSwitch extends BuildingObject implements Interactive, Visible
{
	Light linkedLight;

	public LightSwitch( int x, int y, Light light )
	{
		super( x, y, Constants.LIGHT_SWITCH_WIDTH, Constants.LIGHT_SWITCH_HEIGHT );
		this.linkedLight = light;
	}

	void flip()
	{
		if ( this.linkedLight.isOn() )
		{
			this.linkedLight.turnOff();
		}
		else
		{
			this.linkedLight.turnOn();
		}
	}

	@Override
	public void paint( Graphics2D g2d )
	{
		// TODO Auto-generated method stub

	}

	/**
	 * Flip the current light switch.
	 */
	@Override
	public void interact( BuildingObject interacter )
	{
		// Wait for the light switch to stop being in use.
		try
		{
			while ( this.inUse )
			{
				Thread.sleep( 1 );
			}
		}
		catch ( InterruptedException e )
		{
			e.printStackTrace();
		}

		this.inUse = true;
		this.flip();
		this.inUse = false;
	}
}
