package people;

import interactive.Interactive;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import base.BuildingObject;
import base.Visible;
import constants.Constants;

public class Me extends Person implements Interactive, Visible, Runnable /* , KeyListener */
{
	public Me( int x, int y )
	{
		super( x, y );
		try
		{
			this.face = ImageIO.read( new File( "resources/images/person/face/me/_head.png" ) );
			// this.body = ImageIO.read( new File( "resources/images/person/face/me/_body" ) );
		}
		catch ( IOException e )
		{
			System.out.println( "Could not read character graphics." );
		}
	}

	private String name = "Logan";

	@Override
	public void paint( Graphics2D g2d )
	{
		if ( this.velocityX == 0 )
		{ // Standing still
			this.animationStep[ 0 ] = 0;
			g2d.drawImage(
					this.face.getSubimage( 90, 0, 10, 10 ).getScaledInstance(
							Constants.PERSON_WIDTH, Constants.PERSON_HEAD_HEIGHT, 0 ),
					( int ) this.x, ( int ) this.y - Constants.PERSON_BODY_HEIGHT, null );
		}
		else if ( this.velocityX > 0 )
		{ // Moving to the right
			g2d.drawImage( this.face.getSubimage( 10 * this.animationStep[ 0 ], 10, 10, 10 )
					.getScaledInstance( Constants.PERSON_WIDTH, Constants.PERSON_HEAD_HEIGHT, 0 ),
					( int ) this.x, ( int ) this.y - Constants.PERSON_BODY_HEIGHT, null );
			this.animationStep[ 0 ] = ( this.animationStep[ 0 ] + 1 ) % 9;
		}
		else
		{ // Moving to the left
			g2d.drawImage( this.face.getSubimage( 10 * this.animationStep[ 0 ], 0, 10, 10 )
					.getScaledInstance( Constants.PERSON_WIDTH, Constants.PERSON_HEAD_HEIGHT, 0 ),
					( int ) this.x, ( int ) this.y - Constants.PERSON_BODY_HEIGHT, null );
			this.animationStep[ 0 ] = ( this.animationStep[ 0 ] + 1 ) % 9;
		}
	}
}
