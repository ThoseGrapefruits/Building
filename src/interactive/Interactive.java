package interactive;

import base.BuildingObject;

/**
 * Superclass to be extended by all interactive objects like LightSwitch, Elevator, Door, etc.
 * 
 * @author Logan Moore
 */
public interface Interactive
{
	final boolean interactive = true;

	public void interact( BuildingObject interacter );
}
