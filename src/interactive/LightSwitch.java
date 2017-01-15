package interactive;

import java.awt.*;

import base.BuildingObject;
import base.Interactive;
import base.Visible;
import constants.Constants;
import main.Building;

public class LightSwitch extends BuildingObject implements Interactive, Visible {

    Light linkedLight;

    public LightSwitch(Building building, double x, double y, Light light) {
        super(building, x, y, Constants.LIGHT_SWITCH_WIDTH, Constants.LIGHT_SWITCH_HEIGHT);
        linkedLight = light;
    }

    void flip() {
        if (linkedLight.isOn()) {
            linkedLight.turnOff();
        }
        else {
            linkedLight.turnOn();
        }
    }

    @Override
    public void paint(Graphics2D g2d) {
        // TODO Auto-generated method stub

    }

    /**
     * Flip the current light switch.
     */
    @Override
    public void interact(BuildingObject interacter) {
        // Wait for the light switch to stop being in use.
        try {
            while (inUse) {
                Thread.sleep(1);
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        inUse = true;
        flip();
        inUse = false;
    }
}
