package core;

import java.util.ArrayList;

import people.Chunk;

public class ChunkList extends ArrayList < Chunk >
{

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 4753668060351034418L;

	public void merge( ChunkList other )
	{
		for ( Chunk b : other )
		{
			if ( !this.contains( b ) )
			{
				this.add( b );
			}
		}
	}
}
