package interactive;

import java.awt.Graphics2D;

import main.Building;
import base.BuildingObject;
import base.Interactive;
import base.Visible;
import constants.Constants;

/**
 * A tall, rectangular, flat piece of wood which can be pushed or pulled with a circular metal knob in order to allow transport through a passage.
 * 
 * @author Logan Moore
 */
public class Door extends BuildingObject implements Interactive, Visible
{
	/**
	 * Possible states for the door.
	 */
	private final int CLOSED = 0, OPEN_LEFT = 1, OPEN_RIGHT = 2;

	public Door( Building building, double x, double y )
	{
		super( building, x, y, Constants.DOOR_CLOSED_WIDTH, Constants.DOOR_HEIGHT );
	}

	/**
	 * Whether the door is open or closed, and which direction it is open in.
	 */
	public int open = CLOSED;

	protected boolean passable = false;

	@Override
	public void interact( BuildingObject object )
	{
		this.inUse = true;
		if ( this.open == CLOSED )
		{
			if ( object.x < this.x )
			{
				this.open = OPEN_RIGHT;
				this.passable = true;
			}
			else
			{
				this.open = OPEN_LEFT;
				this.passable = true;
			}
			this.animationStep[ 0 ] = 1;
		}
		else if ( !( this.getBounds().intersects( object.getBounds() ) ) )
		{
			this.open = CLOSED;
			this.passable = false;
		}
		this.inUse = false;
	}

	@Override
	public void paint( Graphics2D g2d )
	{
		if ( this.open == CLOSED )
		{
			this.animationStep[ 0 ] = 0;
			g2d.setColor( Constants.DOOR_COLOR );
			g2d.fillRect( ( int ) this.x, ( int ) this.y, Constants.DOOR_CLOSED_WIDTH,
					Constants.DOOR_HEIGHT );
			g2d.setColor( Constants.DOORKNOB_COLOR );
			// Right door handle
			g2d.fillOval( ( int ) this.x + Constants.DOOR_CLOSED_WIDTH, ( int ) this.y
					+ Constants.DOOR_HEIGHT / 2, 8, 8 );
			g2d.fillRect( ( int ) this.x + Constants.DOOR_CLOSED_WIDTH, ( int ) this.y
					+ Constants.DOOR_HEIGHT / 2 + 2, 4, 4 );

			// Left door handle
			g2d.setColor( Constants.DOORKNOB_COLOR );
			g2d.fillOval( ( int ) this.x - Constants.DOOR_CLOSED_WIDTH, ( int ) this.y
					+ Constants.DOOR_HEIGHT / 2, 8, 8 );
			g2d.fillRect( ( int ) this.x - Constants.DOOR_CLOSED_WIDTH + 4, ( int ) this.y
					+ Constants.DOOR_HEIGHT / 2 + 2, 4, 4 );
		}
		else if ( this.open == OPEN_LEFT )
		{
			if ( this.animationStep[ 0 ] < 100 )
			{
				this.animationStep[ 0 ] = ( int ) Math.ceil( this.animationStep[ 0 ] * 1.2 );
			}
			g2d.setColor( Constants.DOOR_COLOR );
			g2d.fillRect( ( int ) ( this.x - Constants.DOOR_OPEN_WIDTH
					* ( this.animationStep[ 0 ] / 100.0 ) ), ( int ) this.y,
					( int ) ( Constants.DOOR_OPEN_WIDTH * this.animationStep[ 0 ] / 100 ),
					Constants.DOOR_HEIGHT );
			g2d.setColor( Constants.DOORKNOB_COLOR );
			g2d.fillOval( ( int ) ( this.x - Constants.DOOR_OPEN_WIDTH * this.animationStep[ 0 ]
					/ 100 + 4 ), ( int ) this.y + Constants.DOOR_HEIGHT / 2, 8, 8 );
		}
		else if ( this.open == OPEN_RIGHT )
		{
			if ( this.animationStep[ 0 ] < 100 )
			{
				this.animationStep[ 0 ] = ( int ) Math.ceil( this.animationStep[ 0 ] * 1.2 );
			}
			g2d.setColor( Constants.DOOR_COLOR );
			g2d.fillRect( ( int ) this.x, ( int ) this.y, ( int ) ( Constants.DOOR_OPEN_WIDTH
					* this.animationStep[ 0 ] / 100 ), Constants.DOOR_HEIGHT );
			g2d.setColor( Constants.DOORKNOB_COLOR );
			g2d.fillOval( ( int ) ( this.x + Constants.DOOR_OPEN_WIDTH * this.animationStep[ 0 ]
					/ 100 - 12 ), ( int ) this.y + Constants.DOOR_HEIGHT / 2, 8, 8 );
		}
	}
}
