package people;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Building;
import base.Interactive;
import base.Visible;
import constants.Constants;

public class Tai extends Person implements Interactive, Visible, Runnable
{
	public Tai( Building building, double x, double y )
	{
		super( building, x, y );

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
