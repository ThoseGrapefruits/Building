package interactive;

import java.awt.*;

import base.BuildingObject;
import base.Interactive;
import base.Visible;
import constants.Constants;
import main.Building;

/**
 * A tall, rectangular, flat piece of wood which can be pushed or pulled with a circular metal knob in
 * order to allow transport through a passage.
 *
 * @author Logan Moore
 */
public class Door extends BuildingObject implements Interactive, Visible {

    /**
     * Possible states for the door.
     */
    private final int CLOSED = 0, OPEN_LEFT = 1, OPEN_RIGHT = 2;
    /**
     * Whether the door is open or closed, and which direction it is open in.
     */
    public int open = CLOSED;
    protected boolean passable = false;

    public Door(Building building, double x, double y) {
        super(building, x, y, Constants.DOOR_CLOSED_WIDTH, Constants.DOOR_HEIGHT);
    }

    @Override
    public void interact(BuildingObject object) {
        inUse = true;
        if (open == CLOSED) {
            if (object.getX() < x) {
                open = OPEN_RIGHT;
                passable = true;
            }
            else {
                open = OPEN_LEFT;
                passable = true;
            }
            animationStep[0] = 1;
        }
        else if (!(getBounds().intersects(object.getBounds()))) {
            open = CLOSED;
            passable = false;
        }
        inUse = false;
    }

    @Override
    public void paint(Graphics2D g2d) {
        if (open == CLOSED) {
            animationStep[0] = 0;
            g2d.setColor(Constants.DOOR_COLOR);
            g2d.fillRect((int) x, (int) y, Constants.DOOR_CLOSED_WIDTH,
                         Constants.DOOR_HEIGHT);
            g2d.setColor(Constants.DOORKNOB_COLOR);
            // Right door handle
            g2d.fillOval((int) x + Constants.DOOR_CLOSED_WIDTH, (int) y
                                                                + Constants.DOOR_HEIGHT / 2, 8, 8);
            g2d.fillRect((int) x + Constants.DOOR_CLOSED_WIDTH, (int) y
                                                                + Constants.DOOR_HEIGHT / 2 + 2, 4,
                         4);

            // Left door handle
            g2d.setColor(Constants.DOORKNOB_COLOR);
            g2d.fillOval((int) x - Constants.DOOR_CLOSED_WIDTH, (int) y
                                                                + Constants.DOOR_HEIGHT / 2, 8, 8);
            g2d.fillRect((int) x - Constants.DOOR_CLOSED_WIDTH + 4, (int) y
                                                                    + Constants.DOOR_HEIGHT / 2 + 2,
                         4, 4);
        }
        else if (open == OPEN_LEFT) {
            if (animationStep[0] < 100) {
                animationStep[0] = (int) Math.ceil(animationStep[0] * 1.2);
            }
            g2d.setColor(Constants.DOOR_COLOR);
            g2d.fillRect((int) (x - Constants.DOOR_OPEN_WIDTH
                                    * (animationStep[0] / 100.0)), (int) y,
                         (int) (Constants.DOOR_OPEN_WIDTH * animationStep[0] / 100),
                         Constants.DOOR_HEIGHT);
            g2d.setColor(Constants.DOORKNOB_COLOR);
            g2d.fillOval((int) (x - Constants.DOOR_OPEN_WIDTH * animationStep[0]
                                    / 100 + 4), (int) y + Constants.DOOR_HEIGHT / 2, 8, 8);
        }
        else if (open == OPEN_RIGHT) {
            if (animationStep[0] < 100) {
                animationStep[0] = (int) Math.ceil(animationStep[0] * 1.2);
            }
            g2d.setColor(Constants.DOOR_COLOR);
            g2d.fillRect((int) x, (int) y, (int) (Constants.DOOR_OPEN_WIDTH
                                                  * animationStep[0] / 100), Constants.DOOR_HEIGHT);
            g2d.setColor(Constants.DOORKNOB_COLOR);
            g2d.fillOval((int) (x + Constants.DOOR_OPEN_WIDTH * animationStep[0]
                                    / 100 - 12), (int) y + Constants.DOOR_HEIGHT / 2, 8, 8);
        }
    }
}
