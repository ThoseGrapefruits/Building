package boundaries;

import java.awt.Graphics2D;

import constants.Constants;

public class Wall extends Boundary
{
	public Wall( int x, int y, int height )
	{
		super( x, y, Constants.WALL_WIDTH, height );
	}

	@Override
	public void paint( Graphics2D g2d )
	{
		g2d.setColor( Constants.WALL_COLOR );
		g2d.fillRect( ( int ) this.x, ( int ) this.y, this.width, this.height );
	}
}
