package boundaries;

import base.BuildingObject;
import base.Visible;
import main.Building;

/**
 * Basic boundary, cannot be passed through by moving objects.
 *
 * @author Logan Moore
 */
public abstract class Boundary extends BuildingObject implements Visible {
	protected boolean passable = false;

	Boundary( Building building, double x, double y, int width, int height ) {
		super( building, x, y, width, height );
	}
}
