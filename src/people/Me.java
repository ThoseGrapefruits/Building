package people;

import interactive.Interactive;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import base.Visible;

public class Me extends Person implements Interactive, Visible, Runnable
{
	public Me( int x, int y )
	{
		super( x, y );

		try
		{
			this.face = ImageIO.read( new File( "resources/images/person/face/_sheen.png" ) );
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}

	@Override
	public void paint( Graphics2D g2d )
	{
		g2d.drawImage( this.face.getScaledInstance( 40, 40, 0 ), this.x, this.y, null );
	}
}
