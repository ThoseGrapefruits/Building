package boundaries;

import java.awt.Graphics2D;

import main.Building;
import constants.Constants;

/**
 * Wall boundary, prevents people from walking horizontally.
 * 
 * @author Logan Moore
 */
public class Wall extends Boundary
{
	public Wall( Building building, double x, double y, int height )
	{
		super( building, x, y, Constants.WALL_WIDTH, height );
	}

	@Override
	public void paint( Graphics2D g2d )
	{
		g2d.setColor( Constants.WALL_COLOR );
		g2d.fillRect( ( int ) this.x, ( int ) this.y, this.width, this.height );
	}
}
