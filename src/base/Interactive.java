package base;

/**
 * Superclass to be extended by all interactive objects like LightSwitch, Elevator, Door, etc.
 *
 * @author Logan Moore
 */
public interface Interactive {

    /**
     * Declare the object as interactive (overrides the <code>interactive = false</code> from
     * <code>BuildingObject</code>.
     */
    final boolean interactive = true;

    /**
     * Function to call when an object (usually a <code>Person</code>) "interacts" with an object.
     */
    public void interact(BuildingObject interacter);
}
