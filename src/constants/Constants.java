package constants;

public class Constants
{
	/**
	 * Construction standards. All values are in pixels.
	 */
	public static final int BUILDING_WIDTH = 700;

	/**
	 * Barrier constants
	 */
	public static final int WALL_WIDTH = 5, FLOOR_HEIGHT = 5, FLOOR_DISTANCE = 40;

	/**
	 * Interactive object standards.
	 */
	public static final int ELEVATOR_BUTTON_WIDTH = 3, ELEVATOR_BUTTON_HEIGHT = 5,
			LIGHT_SWITCH_WIDTH = 3, LIGHT_SWITCH_HEIGHT = 5, ELEVATOR_WIDTH = 40,
			ELEVATOR_SHAFT_WIDTH = ELEVATOR_WIDTH + 10, ELEVATOR_HEIGHT = 30, DOOR_WIDTH = 20,
			DOOR_HEIGHT = 30, LIGHT_WIDTH = 5, LIGHT_HEIGHT = 5;

	/**
	 * Speed limits, in pixels per tick.
	 */
	public static final int ELEVATOR_MAX_VELOCITY = 10;

	/**
	 * Rendering constants
	 */
	public static final int WINDOW_WIDTH = 800, WINDOW_HEIGHT = 600;

	/**
	 * People dimensions
	 */
	public static final int PERSON_WIDTH = 10, PERSON_HEIGHT = 25;

	/**
	 * Floors, for reference.
	 */
	public static final int GROUND_FLOOR = 0, FIRST_FLOOR = 0, FLOOR_1 = 0, SECOND_FLOOR = 1,
			FLOOR_2 = 1, THIRD_FLOOR = 2, FLOOR_3 = 2, FOURTH_FLOOR = 3, FLOOR_4 = 3;
}
