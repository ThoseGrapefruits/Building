package interactive;

public class LightSwitch extends Interactive
{
	Light linkedLight;

	LightSwitch( int x, int y, Light light )
	{
		this.x = x;
		this.y = y;
		this.linkedLight = light;
	}

	/**
	 * Coordinates of object
	 */
	int x = 0;
	int y = 0;

	void flip()
	{
		if ( linkedLight.isOn() )
		{
			linkedLight.turnOff();
		}
		else
		{
			linkedLight.turnOn();
		}
	}
}
