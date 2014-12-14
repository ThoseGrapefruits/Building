package people;

import base.BuildingObject;
import base.Force;
import base.Interactive;
import base.Visible;
import base.ChunkMap;
import base.Moveable;
import boundaries.Floor;
import boundaries.Wall;
import constants.Constants;
import interactive.Door;
import main.Building;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Map;

public class Chunk extends BuildingObject implements Interactive, Runnable, Visible, Moveable
{
	public static void main( String[] args )
	{
		getForceTest();
	}

	/**
	 * Other blobs connected to the current blob.
	 */
	public ChunkMap connected = new ChunkMap();

	public ChunkObject parent;

	/**
	 * Mass of the given <code>Chunk</code>, in arbitrary units.
	 */
	public double mass = 1.0;

	/**
	 * Creates a new chunk.
	 *
	 * @param building
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param mass is the mass of the <code>Chunk</code>.
	 */
	public Chunk( Building building, double x, double y, int width, int height, ChunkObject parent,
			double mass )
	{
		super( building, x, y, width, height );
		this.mass = mass;
		this.parent = parent;
	}

	public void connect( double distance, Chunk c )
	{
		this.connected.put( distance, c );
		c.connected.put( distance, this );
	}

	public void connect( Chunk c )
	{
		this.connect( Math.sqrt( c.x * c.x + c.y * c.y ), c );
	}

	public void connect( ArrayList<Chunk> chunks )
	{
		for ( Chunk c : chunks )
		{
			this.connect( c );
		}
	}

	/**
	 * Whether force has been applied to the current Blob already.
	 */
	boolean forceApplied = false;

	public Force getForce()
	{
		// Forces to be applied to the current object
		double forceX = 0, forceY = 0;

		// Distance between the given two objects
		double distX;
		double distY;

		// The differences between the desired distance and the actual distance
		// of two given chunks.
		double diff;

		// Direction of the force, in radians
		double dir;

		for ( Map.Entry<Double, Chunk> cursor : this.connected.entrySet() )
		{
			distX = ( this.x - cursor.getValue().x );
			distY = ( this.y - cursor.getValue().y );
			System.out.println( "distX: " + distX + "\tdistY: " + distY );

			diff = Math.sqrt( ( distX * distX + distY * distY ) );
			System.out.println( "diff: " + diff );

			dir = Math.atan( distY / distX );

			// NOTE: This math may look wrong, but it's because of pixel
			// coordinates and my desire to have the normal quadrants.
			if ( distX < 0 )
			{
				if ( distY < 0 ) // 3rd quadrant
				{
					dir += Math.PI * 3 / 2;
				}
				else
				// 2nd quadrant
				{
					dir += Math.PI / 2;
				}
			}
			else if ( distY < 0 ) // 4th quadrant
			{
				dir += Math.PI;
			}

			System.out.println( "dir: " + ( dir * 180 / Math.PI ) );

			forceX += Math.cos( dir ) * diff;
			forceY += Math.sin( dir ) * diff;
			System.out.println( "forceX: " + forceX + "\tForceY: " + forceY );
		}
		return new Force( Math.sqrt( forceX * forceX + forceY * forceY ), Math.atan( forceY / forceX )
				* 180 / Math.PI );
	}

	public static void getForceTest()
	{
		Building b = new Building();
		ChunkObject co = new ChunkObject( b, 10, 10 );
		Chunk c1 = new Chunk( b, 10, 10, 10, 10, co, 1 );
		Chunk c2 = new Chunk( b, 20, 20, 10, 10, co, 1 );
		c1.connect( 10, c2 );
		c2.applyForce( c2.getForce() );
		System.out.println( "c2-post: " + c2 );
	}

	public void applyForce( Force f )
	{
		if ( !this.forceApplied )
		{
			this.forceApplied = true;

			this.velocityX += Math.cos( f.getDirection() ) * f.getMagnitude() / this.mass;
			this.velocityY += Math.sin( f.getDirection() ) * f.getMagnitude() / this.mass;

			for ( Map.Entry<Double, Chunk> cursor : this.connected.entrySet() )
			{
				cursor.getValue().applyForce( cursor.getValue().getForce() );
			}
			this.forceApplied = false;
		}
	}

	@Override
	public void paint( Graphics2D g2d )
	{
		Ellipse2D.Double circle = new Ellipse2D.Double( this.x, this.y, this.width, this.height );
		g2d.draw( circle );
		for ( Map.Entry<Double, Chunk> cursor : this.connected.entrySet() )
		{
			Chunk c = cursor.getValue();
			g2d.drawLine( ( int ) ( this.x + ( this.width / 2 ) ),
					( int ) ( this.y + ( this.height / 2 ) ), ( int ) ( c.x + ( c.width / 2 ) ),
					( int ) ( c.y + ( c.height / 2 ) ) );
		}
	}

	@Override
	public void run()
	{
		this.applyForce( this.getForce() );
	}

	@Override
	public void interact( BuildingObject interacter )
	{
		// TODO Auto-generated method stub
	}

	@Override
	public String toString()
	{
		return "Chunk: " + super.toString() + "\n" + this.getForce();
	}

	@Override
	public boolean canMoveX( Building b )
	{
		boolean canMoveX = true;
		Rectangle nextLocation = new Rectangle( ( int ) ( this.x + this.velocityX ),
				( int ) ( this.y + this.velocityY ), this.width, this.height );

		for ( Wall wall : b.walls )
		{
			if ( nextLocation.intersects( wall.getBounds() ) )
			{
				canMoveX = false;
			}
		}
		for ( Door door : b.doors )
		{
			if ( door.open == 0 && nextLocation.intersects( door.getBounds() ) )
			{
				canMoveX = false;
			}
		}
		return canMoveX;
	}

	@Override
	public boolean canMoveY( Building b )
	{
		boolean canMoveY = true;
		Rectangle nextLocation = new Rectangle( ( int ) ( this.x + this.velocityX ),
				( int ) ( this.y + this.velocityY ), this.width, this.height );
		for ( Floor floor : b.floors )
		{
			if ( nextLocation.intersects( floor.getBounds() ) )
			{
				canMoveY = false;
			}
		}
		return canMoveY;
	}

	@Override
	public void move( Building b )
	{
		if ( this.canMoveX( b ) )
		{
			this.x += this.velocityX;
		}
		else
		{
			this.velocityX = 0;
		}

		if ( this.canMoveY( b ) )
		{
			if ( this.velocityY < Constants.TERMINAL_VELOCITY )
			{
				this.velocityY += 0.1;
			}
			this.y += this.velocityY;
		}
		else
		{
			this.velocityY = 0;
		}
	}
}
