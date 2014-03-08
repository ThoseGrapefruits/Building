package interactive;

public class Light
{
	Light( int x, int y )
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * Coordinates of Light
	 */
	int x = 0;
	int y = 0;

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
}
