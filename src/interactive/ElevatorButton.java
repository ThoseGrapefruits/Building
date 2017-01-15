package interactive;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import base.BuildingObject;
import base.Interactive;
import base.Visible;
import constants.Constants;
import main.Building;

/**
 * The buttons you press to make the big metal box go up or down.
 *
 * @author Logan Moore
 */
public class ElevatorButton extends BuildingObject implements Interactive, Visible, Runnable,
                                                              ActionListener {

    /**
     * The floor location of the button.
     */
    public int floor;
    /**
     * Whether the elevator is on the same floor as the button
     */
    public boolean elevatorOnCurrentFloor = false;
    /**
     * The elevator to be called when the button is pressed.
     */
    Elevator linkedElevator;

    /**
     * Creates a new button with a linked elevator and a floor index.
     */
    public ElevatorButton(Building building, double x, double y, Elevator linkedElevator,
                          int floor) {
        super(building, x, y, Constants.ELEVATOR_BUTTON_BACKING_WIDTH,
              Constants.ELEVATOR_BUTTON_BACKING_HEIGHT);
        this.linkedElevator = linkedElevator;
        this.floor = floor;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
    }

    @Override
    public void interact(BuildingObject interacter) {
        linkedElevator.call(this);
    }

    @Override
    public void paint(Graphics2D g2d) {
        g2d.setColor(Constants.BUTTON_BACKING_COLOR);
        g2d.fillRect((int) x, (int) y, width, height);
        if (elevatorOnCurrentFloor) {
            g2d.setColor(Color.RED);
        }
        else {
            g2d.setColor(Constants.BUTTON_COLOR);
        }
        g2d.fillRoundRect((int) x + width / 4, (int) y + height / 8,
                          Constants.ELEVATOR_BUTTON_WIDTH, Constants.ELEVATOR_BUTTON_HEIGHT,
                          Constants.ELEVATOR_BUTTON_WIDTH / 2, Constants.ELEVATOR_BUTTON_HEIGHT / 2);
        g2d.fillRoundRect((int) x + width / 4, (int) y + height
                                               - Constants.ELEVATOR_BUTTON_HEIGHT - 2,
                          Constants.ELEVATOR_BUTTON_WIDTH,
                          Constants.ELEVATOR_BUTTON_HEIGHT, Constants.ELEVATOR_BUTTON_WIDTH / 2,
                          Constants.ELEVATOR_BUTTON_HEIGHT / 2);
        if (drawBounds) {
            drawBounds(g2d);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        linkedElevator.call(this);
    }
}
