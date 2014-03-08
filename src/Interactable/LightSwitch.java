package interactable;

public class LightSwitch
{
	Light linkedLight;

	LightSwitch( Light light )
	{
		this.linkedLight = light;
	}

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
