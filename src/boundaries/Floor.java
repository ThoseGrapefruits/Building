package boundaries;

import java.awt.Color;
import java.awt.Graphics2D;

import constants.Constants;

public class Floor extends Boundary
{
	/**
	 * Indicates which floor the given floor is.
	 */
	int number = 0;

	Floor( int x, int y, int width, int number )
	{
		super( x, y, width, Constants.FLOOR_HEIGHT );
		this.number = number;
		this.width = width;
	}

	Floor( int x, int y, int number )
	{
		super( x, y, Constants.BUILDING_WIDTH, Constants.FLOOR_HEIGHT );
		this.number = number;
	}

	@Override
	public void paint( Graphics2D g2d )
	{
		g2d.setColor( new Color( 20, 20, 20 ) );
		g2d.fillRect( ( int ) this.x, ( int ) this.y, this.width, this.height );
	}
}
