package people;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import core.ChunkList;
import main.Building;
import base.BuildingObject;
import base.Force;
import base.Interactive;
import base.Visible;

public class Chunk extends BuildingObject implements Interactive, Runnable, Visible
{
	/**
	 * Other blobs connected to the current blob.
	 */
	public ChunkList connected = new ChunkList();

	public Chunk( Building building, double x, double y, int width, int height, int distance )
	{
		super( building, x, y, width, height );
	}

	public void connect( Chunk b )
	{
		this.connected.add( b );
		b.connected.add( this );
	}

	/**
	 * Whether force has been applied to the current Blob already.
	 */
	boolean forceApplied = false;

	public Force getForce()
	{
		// TODO write force calculations
		return new Force( 0, 0 );
	}

	public void applyForce( Force f )
	{
		if ( !this.forceApplied )
		{
			// TODO modify velocity
		}
		for ( Chunk b : connected )
		{
			b.applyForce( b.getForce() );
		}
	}

	@Override
	public void paint( Graphics2D g2d )
	{
		Ellipse2D.Double circle = new Ellipse2D.Double( this.x, this.y, this.width, this.height );
		g2d.draw( circle );
		for ( Chunk b : this.connected )
		{
			g2d.drawLine( (int)(this.x + ( this.width / 2 )),
					(int)(this.y + ( this.height / 2 )),
					(int)( b.x + (b.width / 2)),
					(int)(b.y + (b.height / 2)));
			b.paint( g2d );
		}
	}

	@Override
	public void run()
	{

	}

	@Override
	public void interact( BuildingObject interacter )
	{
		// TODO Auto-generated method stub
	}
}
