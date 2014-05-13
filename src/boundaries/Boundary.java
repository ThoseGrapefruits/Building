package boundaries;

import main.Building;
import base.BuildingObject;
import base.Visible;

/**
 * Basic boundary, cannot be passed through by moving objects.
 * 
 * @author Logan Moore
 */
public abstract class Boundary extends BuildingObject implements Visible
{
	Boundary( Building building, double x, double y, int width, int height )
	{
		super( building, x, y, width, height );
	}

	protected boolean passable = false;
}
