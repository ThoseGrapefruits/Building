package people;

import interactive.Interactive;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import base.Visible;
import constants.Constants;

public class Tai extends Person implements Interactive, Visible, Runnable
{
	public Tai( int x, int y )
	{
		super( x, y );

		try
		{
			this.head = ImageIO.read( new File( "resources/images/person/face/tai.png" ) );
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}

	@Override
	public void paint( Graphics2D g2d )
	{
		g2d.drawImage( this.head, null, ( int ) this.x, ( int ) this.y
				+ Constants.PERSON_BODY_HEIGHT );
	}
}
