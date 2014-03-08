package boundaries;

import constants.Constants;

public class Floor extends Boundary
{
	int number = 0;

	Floor( int x, int y, int width, int number )
	{
		this.number = number;
	}

	/**
	 * Coordinates of object
	 */
	int x = 0;
	int y = 0;

	/**
	 * Width of floor (usually BUILDING_WIDTH)
	 */
	int width = Constants.BUILDING_WIDTH;
}
