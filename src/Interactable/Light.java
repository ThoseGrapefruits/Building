package interactable;

public class Light
{
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
