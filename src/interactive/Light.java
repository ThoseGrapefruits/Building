package interactive;

import java.awt.Graphics2D;

import main.Building;
import base.BuildingObject;
import base.Visible;
import constants.Constants;

public class Light extends BuildingObject implements Visible
{
	public Light( Building building, double x, double y )
	{
		super( building, x, y, Constants.LIGHT_WIDTH, Constants.LIGHT_HEIGHT );
	}

	/**
	 * Brightness level of the light. Default: 5
	 */
	int brightness = 5;

	/**
	 * Whether the light is currently on or off.
	 */
	private boolean on = false;

	/**
	 * @return if the light is on or off.
	 */
	boolean isOn()
	{
		return on;
	}

	void turnOn()
	{
		on = true;
	}

	void turnOff()
	{
		on = false;
	}

	@Override
	public void paint( Graphics2D g2d )
	{
		// TODO Auto-generated method stub

	}
}
