package boundaries;

public class Wall extends Boundary
{

	Wall( int x, int y, int height )
	{
		this.x = x;
		this.y = y;
		this.height = height;
	}

	/**
	 * Coordinates of object
	 */
	int x = 0;
	int y = 0;

	/**
	 * Height of the wall
	 */
	int height = 0;

}
