package shapes;

import base.BuildingObject;
import base.Visible;
import main.Building;

public abstract class Shape extends BuildingObject implements Visible {

    public Shape(Building building, double x, double y, int width, int height) {
        super(building, x, y, width, height);
    }
}
