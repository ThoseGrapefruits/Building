package constants;

public class Constants
{
	/**
	 * Building (pixels)
	 */
	public static final int BUILDING_WIDTH = 700;

	/**
	 * Barriers (pixels)
	 */
	public static final int WALL_WIDTH = 5, FLOOR_HEIGHT = 5, FLOOR_DISTANCE = 40;

	/**
	 * Interactive objects (pixels)
	 */
	public static final int ELEVATOR_BUTTON_WIDTH = 3, ELEVATOR_BUTTON_HEIGHT = 5,
			LIGHT_SWITCH_WIDTH = 3, LIGHT_SWITCH_HEIGHT = 5, ELEVATOR_WIDTH = 40,
			ELEVATOR_SHAFT_WIDTH = ELEVATOR_WIDTH + 10, ELEVATOR_HEIGHT = 30, DOOR_WIDTH = 20,
			DOOR_HEIGHT = 30, LIGHT_WIDTH = 5, LIGHT_HEIGHT = 5;

	/**
	 * Speed limits (pixels / tick)
	 */
	public static final double ELEVATOR_MAX_VELOCITY = 1.0, PERSON_MAX_VELOCITY = 1.0;

	/**
	 * Rendering, scaling, & window management (pixels)
	 */
	public static final int WINDOW_WIDTH = 800, WINDOW_HEIGHT = 600, IMAGE_SCALE = 4;

	/**
	 * Timing (milliseconds)
	 */
	public static final int TICK = 10;

	/**
	 * People (pixels)
	 */
	public static final int PERSON_WIDTH = 40, PERSON_HEIGHT = 100, PERSON_BODY_HEIGHT = 60,
			PERSON_HEAD_HEIGHT = 40;

	/**
	 * Interaction & bounding boxes (pixels)
	 */
	public static final int INTERACTION_DISTANCE_X = 5, INTERACTION_DISTANCE_Y = 5;

	/**
	 * Floors
	 */
	public static final int GROUND_FLOOR = 0, FIRST_FLOOR = 0, FLOOR_1 = 0, SECOND_FLOOR = 1,
			FLOOR_2 = 1, THIRD_FLOOR = 2, FLOOR_3 = 2, FOURTH_FLOOR = 3, FLOOR_4 = 3;
}
