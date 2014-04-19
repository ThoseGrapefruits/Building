package people;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import constants.Constants;
import interactive.Interactive;
import base.Visible;

public class Tai extends Person implements Interactive, Visible, Runnable
{
	public Tai( int x, int y )
	{
		super( x, y );

		try
		{
			this.face = ImageIO.read( new File( "resources/images/person/face/tai.png" ) );
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}

	@Override
	public void paint( Graphics2D g2d )
	{
		g2d.drawImage( this.face, null, ( int ) this.x, ( int ) this.y
				+ Constants.PERSON_BODY_HEIGHT );
	}
}
