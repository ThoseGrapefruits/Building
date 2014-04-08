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
	 * Height of the wall
	 */
	int height = 0;

}
