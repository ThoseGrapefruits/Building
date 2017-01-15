package people;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import base.BuildingObject;
import base.Interactive;
import base.Movable;
import base.Visible;
import boundaries.Floor;
import boundaries.Wall;
import constants.Constants;
import interactive.Door;
import interactive.Elevator;
import interactive.ElevatorButton;
import interactive.LightSwitch;
import main.Building;

public class Person extends BuildingObject implements Interactive, Visible, Runnable, Movable {

    public BuildingObject interactiveObjectWithinReach;
    public double velocityX = 0, velocityY = 0;
    public boolean wantsToInteract = false;
    protected String name = "Anonymous" + (1 + (int) Math.random() * 1000);
    /**
     * Objects that the given person has already interacted with
     * (used to make conversation less repetitive, etc.)
     */
    ArrayList<BuildingObject> interactedWith = new ArrayList<BuildingObject>();
    /**
     * Image used for the face of the given person.
     */
    BufferedImage head;
    /**
     * Image used for the body of the given person.
     */
    BufferedImage body;
    String toBeSaid;
    int time = 0;

    public Person(Building building, double x, double y) {
        super(building, x, y, Constants.PERSON_WIDTH, Constants.PERSON_HEIGHT);

        try {
            head = ImageIO.read(getClass().getResource(
                    "/images/person/standard/_head.png"));
            body = ImageIO.read(getClass().getResource(
                    "/images/person/standard/_body.png"));
        }
        catch (IOException e) {
            System.out.println("Could not read character graphics.");
        }
    }

    /**
     * Checks if any of the floors in the current building are below the given object.
     *
     * @return true if floor is found below the person, false if not.
     */
    public boolean isFloorBelow() {
        Rectangle bounds = new Rectangle((int) x, (int) y + height + 1,
                                         width, 1);
        for (Floor floor : building.floors) {
            if (bounds.intersects(floor.getBounds())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds the closest object to a person within its BoundingBox.
     *
     * @return the interactive object closest to the person
     */
    public BuildingObject getClosestInteractiveObject() {
        Rectangle origBounds = getBounds();
        Rectangle bounds = new Rectangle(origBounds.x - 10, origBounds.y - 10,
                                         origBounds.width + 20, origBounds.height + 20);
        for (Person person : building.people) {
            if (person.getBounds().intersects(bounds)) {
                return person;
            }
        }
        for (LightSwitch lightSwitch : building.lightSwitches) {
            if (lightSwitch.getBounds().intersects(bounds)) {
                return lightSwitch;
            }
        }
        for (Elevator elevator : building.elevators) {
            if (bounds.intersects((new Rectangle((int) elevator.getX(), (int) elevator
                    .getCarHeight(), Constants.ELEVATOR_WIDTH,
                                                 Constants.ELEVATOR_CAR_HEIGHT)))) {
                return elevator;
            }

        }

        for (ElevatorButton elevatorButton : building.elevatorButtons) {
            if (elevatorButton.getBounds().intersects(bounds)) {
                return elevatorButton;
            }
        }

        for (Door door : building.doors) {
            if (door.getBounds().intersects(bounds)) {
                return door;
            }
        }
        return null;
    }

    public BufferedImage getHead() {
        return head;
    }

    public String getName() {
        return name;
    }

    /**
     * Method for person-to-person interaction
     *
     * @param otherPerson in the person interacting with the current person.
     */
    public void interact(Person otherPerson) {
        inUse = true;
        otherPerson.velocityX = 0;
        otherPerson.inUse = true;
        animationStep[2] = 255;
        otherPerson.animationStep[2] = 255;

        if (interactedWith.contains(otherPerson)) { // Already interacted with
            otherPerson.toBeSaid = "Hi.";
            toBeSaid = "Hi.";
        }
        else { // Seen for the first time
            interactedWith.add(otherPerson);
            otherPerson.interactedWith.add(this);

            toBeSaid = "Hi, my name is " + name;
            otherPerson.toBeSaid = "Nice to meet you " + name + ", my name is "
                                   + otherPerson.name;
            otherPerson.animationStep[2] = 255;
        }

        inUse = false;
        otherPerson.inUse = false;
    }

    @Override
    public void run() {
        boolean LEFT = false;
        boolean RIGHT = true;
        boolean direction = true;

        while (true) {
            // Movement
            if (velocityX == 0) {
                if (direction == RIGHT) {
                    Rectangle bounds = new Rectangle((int) x, (int) y,
                                                     width + 1, height);
                    boolean wasADoor = false;
                    for (Door door : building.doors) {
                        if (door.getBounds().intersects(bounds)) {
                            wasADoor = true;
                            door.interact(this);
                        }
                    }
                    if (wasADoor) {
                        velocityX = 1;
                    }
                    else {
                        direction = LEFT;
                        velocityX = -1;
                    }
                }
                else {
                    Rectangle bounds = new Rectangle((int) x - 1, (int) y,
                                                     width, height);
                    boolean wasADoor = false;
                    for (Door door : building.doors) {
                        if (door.getBounds().intersects(bounds)) {
                            wasADoor = true;
                            door.interact(this);
                        }
                    }
                    if (wasADoor) {
                        velocityX = -1;
                    }
                    else {
                        direction = RIGHT;
                        velocityX = 1;
                    }
                }
            }

            // Interaction
            /*
			 * for ( Person person : building.people )
			 * {
			 * if ( person.getBounds().intersects( getBounds() ) && !person.equals( this ) )
			 * {
			 * person.interact( this );
			 * }
			 * }
			 * if ( building.me.getBounds().intersects( getBounds() ) )
			 * {
			 * building.me.interact( this );
			 * }
			 */

            // Delay
            try {
                Thread.sleep(Constants.AI_CYCLE);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void interact(BuildingObject object) {
        inUse = true;
        toBeSaid =
                "This shouldn't even happen, because that would imply some object is interacting with a person. Only people should interact with other people.";
        inUse = false;
    }

    public void say(Graphics2D g2d) {
        if (toBeSaid != null && toBeSaid != "" && animationStep[2] != 0) {
            int textWidth = toBeSaid.length() * 3;
            g2d.setColor(new Color(0, 0, 0, animationStep[2]));
            g2d.drawString(toBeSaid, (int) x - ((textWidth - width) / 2),
                           (int) y - Constants.TEXT_BOX_DISTANCE);
            animationStep[2]--;
        }
    }

    @Override
    public boolean canMoveX(Building b) {
        boolean canMoveX = true;
        Rectangle nextLocation = new Rectangle((int) (x + velocityX),
                                               (int) (y + velocityY), width, height);

        for (Wall wall : b.walls) {
            if (nextLocation.intersects(wall.getBounds())) {
                canMoveX = false;
            }
        }
        for (Door door : b.doors) {
            if (door.open == 0 && nextLocation.intersects(door.getBounds())) {
                canMoveX = false;
            }
        }
        return canMoveX;
    }

    @Override
    public boolean canMoveY(Building b) {
        boolean canMoveY = true;
        Rectangle nextLocation = new Rectangle((int) (x + velocityX),
                                               (int) (y + velocityY), width, height);
        for (Floor floor : b.floors) {
            if (nextLocation.intersects(floor.getBounds())) {
                canMoveY = false;
            }
        }
        return canMoveY;
    }

    @Override
    public void move(Building b) {
        if (canMoveX(b)) {
            x += velocityX;
        }
        else {
            velocityX = 0;
        }

        if (canMoveY(b)) {
            if (velocityY < Constants.TERMINAL_VELOCITY) {
                velocityY += 0.1;
            }
            y += velocityY;
        }
        else {
            velocityY = 0;
        }
    }

    @Override
    public void paint(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        if (velocityX == 0) { // Standing still
            // Head
            animationStep[0] = 0;
            g2d.drawImage(
                    head.getSubimage(0, 20, 10, 10).getScaledInstance(
                            Constants.PERSON_WIDTH, Constants.PERSON_HEAD_HEIGHT, 0),
                    (int) x, (int) y, null);

            // Body
            animationStep[1] = 0;
            g2d.drawImage(
                    body.getSubimage(0, 30, 10, 15).getScaledInstance(
                            Constants.PERSON_WIDTH, Constants.PERSON_BODY_HEIGHT, 0),
                    (int) x, (int) y + Constants.PERSON_HEAD_HEIGHT, null);
        }
        else if (velocityX > 0) { // Moving to the right
            // Head
            g2d.drawImage(
                    head.getSubimage(
                            10 * (animationStep[0] - (animationStep[0] % 9)) / 9,
                            10, 10, 10).getScaledInstance(Constants.PERSON_WIDTH,
                                                          Constants.PERSON_HEAD_HEIGHT, 0), (int) x,
                    (int) y,
                    null);
            animationStep[0] = (animationStep[0] + 1) % 81;

            // Body
            g2d.drawImage(
                    body
                            .getSubimage(
                                    10 * (animationStep[1] - (animationStep[1]
                                                              % 10)) / 10,
                                    15, 10, 15).getScaledInstance(Constants.PERSON_WIDTH,
                                                                  Constants.PERSON_BODY_HEIGHT, 0),
                    (int) x,
                    (int) y + Constants.PERSON_HEAD_HEIGHT, null);
            animationStep[1] = (animationStep[1] + 1) % 100;
        }
        else { // Moving to the left
            // Head
            g2d.drawImage(
                    head.getSubimage(
                            10 * (animationStep[0] - (animationStep[0] % 9)) / 9,
                            0, 10, 10).getScaledInstance(Constants.PERSON_WIDTH,
                                                         Constants.PERSON_HEAD_HEIGHT, 0), (int) x,
                    (int) y,
                    null);
            animationStep[0] = (animationStep[0] + 1) % 81;

            // Body
            g2d.drawImage(
                    body
                            .getSubimage(
                                    10 * (animationStep[1] - (animationStep[1]
                                                              % 10)) / 10,
                                    0, 10, 15).getScaledInstance(Constants.PERSON_WIDTH,
                                                                 Constants.PERSON_BODY_HEIGHT, 0),
                    (int) x,
                    (int) y + Constants.PERSON_HEAD_HEIGHT, null);
            animationStep[1] = (animationStep[1] + 1) % 100;
        }

        say(g2d);

        if (drawBounds) {
            drawBounds(g2d);
        }
    }
}
