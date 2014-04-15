package boundaries;

import base.BuildingObject;
import base.Visible;

public abstract class Boundary extends BuildingObject implements Visible
{
	Boundary( int x, int y, int width, int height )
	{
		super( x, y, width, height );
	}

	protected boolean passable = false;
}
