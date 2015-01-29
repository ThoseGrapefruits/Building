package base;

/**
 * A <pre>Force</pre> is a magnitude (of arbitrary units) and direction (stored
 * in degrees, accessible in both degrees and radians).
 *
 * @author Logan Moore
 */
public class Force {
	/**
	 * The magnitude of the force (arbitrary units)
	 */
	private double magnitude = 0;
	/**
	 * The direction of the force, in degrees.
	 */
	private double direction = 0;

	/**
	 * Creates a new <pre>Force</pre> with the given magnitude and direction.
	 *
	 * @param magnitude is the magnitude of the new <pre>Force</pre>.
	 * @param direction is the direction of the new <pre>Force</pre>.
	 */
	public Force( double magnitude, double direction ) {
		this.magnitude = magnitude;
		this.direction = direction;
	}

	/**
	 * Returns the current magnitude of the force.
	 *
	 * @return the current magnitude of the force.
	 */
	public double getMagnitude() {
		return magnitude;
	}

	/**
	 * Sets the magnitude to the given value.
	 *
	 * @param magnitude is the new magnitude.
	 */
	public void setMagnitude( double magnitude ) {
		this.magnitude = magnitude;
	}

	/**
	 * Returns the current direction of the force (in degrees).
	 *
	 * @return the current direction of the force (in degrees).
	 */
	public double getDirection() {
		return this.getDirectionDegrees();
	}

	/**
	 * Sets the direction to the given value (in degrees).
	 *
	 * @param direction is the new direction (in degrees).
	 */
	public void setDirection( double direction ) {
		setDirectionDegrees( direction );
	}

	/**
	 * Returns the current direction of the force (in degrees).
	 *
	 * @return the current direction of the force (in degrees).
	 */
	public double getDirectionDegrees() {
		return direction;
	}

	/**
	 * Sets the direction to the given value (in degrees).
	 *
	 * @param direction is the new direction (in degrees).
	 */
	public void setDirectionDegrees( double direction ) {
		this.direction = direction;
	}

	/**
	 * Returns the current direction of the force (in radians).
	 *
	 * @return the current direction of the force (in radians).
	 */
	public double getDirectionRadians() {
		return direction * Math.PI / 180;
	}

	/**
	 * Sets the direction to the given value (in radians).
	 *
	 * @param direction is the new direction (in radians).
	 */
	public void setDirectionRadians( double direction ) {
		this.direction = direction * 180 / Math.PI;
	}

	@Override
	public String toString() {
		return "Magnitude: " + getMagnitude() + "\nDirection: " + getDirection();
	}
}
