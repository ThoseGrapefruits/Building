package boundaries;

import java.awt.*;

import constants.Constants;
import main.Building;

/**
 * Floor boundary, prevents people from falling into the abyss.
 *
 * @author Logan Moore
 */
public class Floor extends Boundary {

    /**
     * Indicates which floor the given floor is.
     */
    int number = 0;

    public Floor(Building building, double x, double y, int width, int number) {
        super(building, x, y, width, Constants.FLOOR_HEIGHT);
        this.number = number;
    }

    public Floor(Building building, double x, double y, int number) {
        super(building, x, y, Constants.BUILDING_WIDTH, Constants.FLOOR_HEIGHT);
        this.number = number;
    }

    @Override
    public void paint(Graphics2D g2d) {
        g2d.setColor(Constants.FLOOR_COLOR);
        g2d.fillRect((int) x, (int) y, width, height);
    }
}
