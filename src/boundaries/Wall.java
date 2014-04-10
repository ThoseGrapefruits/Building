package boundaries;

import java.awt.Graphics;
import java.awt.Graphics2D;

import constants.Constants;

public class Wall extends Boundary
{
	Wall( int x, int y, int height )
	{
		super( x, y, Constants.WALL_WIDTH, height );
	}

	@Override
	public void paint( Graphics2D g2d )
	{
		// TODO Auto-generated method stub

	}
}
