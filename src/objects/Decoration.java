package objects;

import base.BuildingObject;
import base.Interactive;
import base.Visible;
import main.Building;

public abstract class Decoration extends BuildingObject implements Interactive, Visible, Runnable {
	protected Decoration( Building building, double x, double y, int width, int height) {
		super( building, x, y, width, height );
	}
}
