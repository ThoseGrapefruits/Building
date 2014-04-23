package boundaries;

import java.awt.Color;
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
		g2d.setColor( new Color( 20, 20, 20 ) );
		g2d.fillRect( ( int ) this.x, ( int ) this.y, this.width, this.height );
	}
}
