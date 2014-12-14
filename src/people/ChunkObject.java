package people;

import base.BuildingObject;
import base.Visible;
import base.Moveable;
import boundaries.Floor;
import boundaries.Wall;
import constants.Constants;
import interactive.Door;
import main.Building;

import java.awt.*;
import java.util.ArrayList;

public class ChunkObject extends BuildingObject implements Visible, Runnable, Moveable
{
	/**
	 * List of <code>chunks</code> that make up the <code>ChunkObject</code>. 
	 */
	public ArrayList<Chunk> chunks = new ArrayList<Chunk>();

	public ChunkObject( Building building, double x, double y )
	{
		super( building, x, y, 1, 1 );
	}

	@Override
	public int getWidth()
	{
		Chunk r = getRightmostChunk();
		Chunk l = getLeftmostChunk();
		int w = ( int ) ( ( r.x + r.getWidth() ) - l.x );
		this.width = w;
		return w;
	}

	@Override
	public int getHeight()
	{
		Chunk t = getTopmostChunk();
		int h = ( int ) ( ( t.y + t.getHeight() ) - getBottommostChunk().y );
		this.height = h;
		return h;
	}

	/**
	 * Adds the given chunk to the ChunkObject
	 * @param c
	 */
	public void addChunk( Chunk c )
	{
		if ( !chunks.contains( c ) )
		{
			chunks.add( c );
		}
	}

	private Chunk getRightmostChunk()
	{
		Chunk rightmost = chunks.get( 0 );
		for ( Chunk c : chunks )
		{
			if ( c.x > rightmost.x )
			{
				rightmost = c;
			}
		}
		return rightmost;
	}

	private Chunk getLeftmostChunk()
	{
		Chunk leftmost = chunks.get( 0 );
		for ( Chunk c : chunks )
		{
			if ( c.x < leftmost.x )
			{
				leftmost = c;
			}
		}
		return leftmost;
	}

	private Chunk getTopmostChunk()
	{
		Chunk topmost = chunks.get( 0 );
		for ( Chunk c : chunks )
		{
			if ( c.y > topmost.y )
			{
				topmost = c;
			}
		}
		return topmost;
	}

	private Chunk getBottommostChunk()
	{
		Chunk bottommost = chunks.get( 0 );
		for ( Chunk c : chunks )
		{
			if ( c.y < bottommost.y )
			{
				bottommost = c;
			}
		}
		return bottommost;
	}

	@Override
	public void run()
	{
		for ( Chunk c : this.chunks )
		{
			c.run();
		}

		try
		{
			Thread.sleep( Constants.TICK );
		}
		catch ( InterruptedException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void paint( Graphics2D g2d )
	{
		for ( Chunk c : this.chunks )
		{
			c.paint( g2d );
		}
	}

	@Override
	public String toString()
	{
		String output = super.toString() + "\n";
		for ( Chunk c : this.chunks )
		{
			output.concat( c.toString() );
		}
		return output;
	}

	@Override
	public boolean canMoveX( Building b )
	{
		return true;
	}

	@Override
	public boolean canMoveY( Building b )
	{
		return true;
	}

	@Override
	public void move( Building b )
	{
		for (Chunk c : this.chunks)
		{
			c.move( b );
		}
	}
}
