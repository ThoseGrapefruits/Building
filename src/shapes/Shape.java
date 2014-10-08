package shapes;

import main.Building;
import base.BuildingObject;
import base.Visible;

public abstract class Shape extends BuildingObject implements Visible
{
	public Shape( Building building, double x, double y, int width, int height )
	{
		super( building, x, y, width, height );
	}
}
