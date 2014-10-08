package core;

import java.util.ArrayList;

import people.Blob;

public class BlobList extends ArrayList < Blob >
{

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 4753668060351034418L;

	public void merge( BlobList other )
	{
		for ( Blob b : other )
		{
			if ( !this.contains( b ) )
			{
				this.add( b );
			}
		}
	}
}
