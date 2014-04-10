package people;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import interactive.Interactive;
import base.Visible;

public class Tai extends Person implements Interactive, Visible, Runnable
{
	protected Tai( int x, int y, int width, int height )
	{
		super( x, y, width, height );

		try
		{
			this.face = ImageIO.read( new File( "resources/images/people/tai.png" ) );
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}

	@Override
	public void paint( Graphics2D g2d )
	{
		// TODO Auto-generated method stub

	}
}
