package constants;

import java.awt.Color;

/**
 * The various constants to be used throughout the <pre>Building</pre>.
 * 
 * Includes colors, dimensions, speed limits, timing, etc.
 * 
 * @author Logan Moore
 */
public class Constants
{
	/**
	 * Building (pixels)
	 */
	public static final int BUILDING_WIDTH = 700;

	/**
	 * Barriers (pixels)
	 */
	public static final int WALL_WIDTH = 8, FLOOR_HEIGHT = 8, FLOOR_DISTANCE = 125;

	/**
	 * Interactive objects (pixels)
	 */
	public static final int ELEVATOR_BUTTON_BACKING_WIDTH = 8, ELEVATOR_BUTTON_BACKING_HEIGHT = 16,
			DOOR_CLOSED_WIDTH = 8, DOOR_OPEN_WIDTH = 50, ELEVATOR_BUTTON_WIDTH = 4,
			ELEVATOR_BUTTON_HEIGHT = 4, LIGHT_SWITCH_WIDTH = 8, LIGHT_SWITCH_HEIGHT = 8,
			ELEVATOR_WIDTH = DOOR_OPEN_WIDTH * 2 + 4, DOOR_HEIGHT = 110,
			ELEVATOR_CAR_HEIGHT = DOOR_HEIGHT, LIGHT_WIDTH = 5, LIGHT_HEIGHT = 5;

	/**
	 * Speed limits (pixels / tick)
	 */
	public static final double ELEVATOR_MAX_VELOCITY = 1.0, PERSON_MAX_VELOCITY = 1.0,
			TERMINAL_VELOCITY = 5.0;

	/**
	 * Rendering, scaling, & window management (pixels)
	 */
	public static final int WINDOW_WIDTH = 800, WINDOW_HEIGHT = 600, IMAGE_SCALE = 4;

	/**
	 * Timing (milliseconds)
	 */
	public static final int TICK = 10, AI_CYCLE = 100;

	/**
	 * People (pixels)
	 */
	public static final int PERSON_WIDTH = 40, PERSON_HEIGHT = 100, PERSON_BODY_HEIGHT = 60,
			PERSON_HEAD_HEIGHT = 40;

	/**
	 * Text boxes (pixels)
	 */
	public static final int TEXT_BOX_HEIGHT = 20, TEXT_BOX_WIDTH = 150, TEXT_BOX_DISTANCE = 10;

	/**
	 * Interaction & bounding boxes (pixels)
	 */
	public static final int INTERACTION_DISTANCE_X = 5, INTERACTION_DISTANCE_Y = 5;

	/**
	 * Floors
	 */
	public static final int GROUND_FLOOR = 0, FIRST_FLOOR = 0, FLOOR_1 = 0, SECOND_FLOOR = 1,
			FLOOR_2 = 1, THIRD_FLOOR = 2, FLOOR_3 = 2, FOURTH_FLOOR = 3, FLOOR_4 = 3;

	/**
	 * Color scheme.
	 */
	public static final Color BUILDING_COLOR = new Color( 243, 226, 216 ), WALL_COLOR = new Color(
			200, 150, 100 ), FLOOR_COLOR = new Color( 200, 150, 100 ), DOOR_COLOR = new Color( 143,
			106, 96 ), DOORKNOB_COLOR = new Color( 126, 8, 84 ), ELEVATOR_SHAFT_COLOR = new Color(
			50, 50, 50 ), ELEVATOR_CAR_COLOR = new Color( 250, 200, 160 ),
			ELEVATOR_DOOR_COLOR = new Color( 100, 0, 0 ), BUTTON_BACKING_COLOR = new Color( 50, 50,
					50 ), BUTTON_COLOR = new Color( 200, 200, 200 ), SKY_COLOR = new Color( 163, 201,
					244 );

	/**
	 * Number of layers in the rendering path
	 */
	public static final int RENDER_LAYERS = 7;

	/**
	 * Layer for each type of <code>BuildingObject</code>.
	 */
	public static final int LIGHT_SWITCH_LAYER = 0, ELEVATOR_LAYER = 1, DOOR_LAYER = 2,
			LIGHT_LAYER = 3, WALL_LAYER = 4, FLOOR_LAYER = 5, PERSON_LAYER = 6;
}
