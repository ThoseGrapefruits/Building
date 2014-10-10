package people;

import java.util.ArrayList;

import base.BuildingObject;
import main.Building;

public class ChunkObject extends BuildingObject
{
	/**
	 * List of <code>chunks</code> that make up the <code>ChunkObject</code>. 
	 */
	ArrayList < Chunk > chunks = new ArrayList < Chunk >();

	protected ChunkObject( Building building, double x, double y )
	{
		super( building, x, y, 1, 1 );
	}

	@Override
	public int getWidth()
	{
		Chunk r = getRightmostChunk();
		Chunk l = getLeftmostChunk();
		int w = (int)(( r.x + r.getWidth() ) - l.x);
		this.width = w;
		return w;
	}
	
	@Override
	public int getHeight()
	{
		Chunk t = getTopmostChunk();
		Chunk b = getBottommostChunk();
		int h = (int)(( t.y + t.getHeight() ) - b.y);
		this.height = h;
		return h;
	}
	
	/**
	 * Adds the given chunk to the ChunkObject
	 * @param c
	 */
	public void addChunk(Chunk c)
	{
		if (!chunks.contains( c ))
		{
			chunks.add( c );
		}
		for (Chunk linked : c.connected)
		{
			if (!chunks.contains( linked ))
			{
				chunks.add( linked );
			}
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
}
