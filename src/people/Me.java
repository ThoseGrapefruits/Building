package people;

import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;

import base.Interactive;
import base.Visible;
import main.Building;

public class Me extends Person implements Interactive, Visible, Runnable {

    public Me(Building building, double x, double y) {
        super(building, x, y);
        name = "Logan";
        try {
            head = ImageIO.read(getClass().getResource(
                    "/images/person/me/_head.png"));
            body = ImageIO.read(getClass().getResource(
                    "/images/person/me/_body.png"));
        }
        catch (IOException e) {
            System.out.println("Could not read character graphics.");
        }
    }

    /**
     * Animated drawing of user-controlled person.
     */
    @Override
    public void paint(Graphics2D g2d) {
        super.paint(g2d);
    }
}
