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
		if ( this.linkedLight.isOn() )
		{
			this.linkedLight.turnOff();
		}
		else
		{
			this.linkedLight.turnOn();
		}
	}

	public void interact()
	{
		this.inUse = true;
		this.flip();
		this.inUse = false;
	}
}
