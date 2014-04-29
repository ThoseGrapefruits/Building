package boundaries;

import main.Building;
import base.BuildingObject;
import base.Visible;

public abstract class Boundary extends BuildingObject implements Visible
{
	Boundary( Building building, int x, int y, int width, int height )
	{
		super( building, x, y, width, height );
	}

	protected boolean passable = false;
}
