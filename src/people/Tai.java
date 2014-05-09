package people;

import java.awt.Color;
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
			this.head = ImageIO.read( new File( "resources/images/person/Tai/head.png" ) );
			this.body = ImageIO.read( new File( "resources/images/person/default/_body.png" ) );
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}

	@Override
	public void paint( Graphics2D g2d )
	{
		// Head
		g2d.drawImage( this.head, null, ( int ) this.x, ( int ) this.y );
		g2d.setColor( Color.BLACK );
		if ( this.velocityX == 0 )
		{ // Standing still
			// Body
			this.animationStep[ 1 ] = 0;
			g2d.drawImage(
					this.body.getSubimage( 0, 30, 10, 15 ).getScaledInstance(
							Constants.PERSON_WIDTH, Constants.PERSON_BODY_HEIGHT, 0 ),
					( int ) this.x, ( int ) this.y + Constants.PERSON_HEAD_HEIGHT, null );
		}
		else if ( this.velocityX > 0 )
		{ // Moving to the right
			// Body
			g2d.drawImage(
					this.body.getSubimage(
							10 * ( this.animationStep[ 1 ] - ( this.animationStep[ 1 ] % 6 ) ) / 6,
							15, 10, 15 ).getScaledInstance( Constants.PERSON_WIDTH,
							Constants.PERSON_BODY_HEIGHT, 0 ), ( int ) this.x, ( int ) this.y
							+ Constants.PERSON_HEAD_HEIGHT, null );
			this.animationStep[ 1 ] = ( this.animationStep[ 1 ] + 1 ) % 36;
		}
		else
		{ // Moving to the left
			// Body
			g2d.drawImage(
					this.body.getSubimage(
							10 * ( this.animationStep[ 1 ] - ( this.animationStep[ 1 ] % 6 ) ) / 6,
							0, 10, 15 ).getScaledInstance( Constants.PERSON_WIDTH,
							Constants.PERSON_BODY_HEIGHT, 0 ), ( int ) this.x, ( int ) this.y
							+ Constants.PERSON_HEAD_HEIGHT, null );
			this.animationStep[ 1 ] = ( this.animationStep[ 1 ] + 1 ) % 36;
		}

		this.say( g2d );
		this.drawBounds( g2d );
	}
}
